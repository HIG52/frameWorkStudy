package org.example.frameworkstudy.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.entity.Users;
import org.example.frameworkstudy.exception.InvalidUserInputException;
import org.example.frameworkstudy.repository.UserRepository;
import org.example.frameworkstudy.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean userJoin(UserJoinDTO userJoinDTO) {

        if(userJoinDTO.getUserId() == null || userJoinDTO.getUserId().isEmpty()) {
            throw new InvalidUserInputException("사용자 ID는 필수입니다.");
        }

        if(userJoinDTO.getPassword() == null || userJoinDTO.getPassword().isEmpty()) {
            throw new InvalidUserInputException("사용자 Password는 필수입니다.");
        }

        if(userJoinDTO.getName() == null || userJoinDTO.getName().isEmpty()) {
            throw new InvalidUserInputException("사용자 Name는 필수입니다.");
        }

        Users users = Users.builder()
                .userid(userJoinDTO.getUserId())
                .name(userJoinDTO.getName())
                .password(passwordEncoder.encode(userJoinDTO.getPassword()))
                .build();
        userRepository.save(users);
        return true;
    }
}
