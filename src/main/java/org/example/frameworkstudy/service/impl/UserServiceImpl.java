package org.example.frameworkstudy.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.dto.UserLoginDTO;
import org.example.frameworkstudy.entity.Users;
import org.example.frameworkstudy.repository.UserRepository;
import org.example.frameworkstudy.service.UserService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserJoinDTO userJoin(UserJoinDTO userJoinDTO) {
        Users users = Users.builder()
                .userid(userJoinDTO.getUserId())
                .name(userJoinDTO.getName())
                .password(userJoinDTO.getPassword())
                .build();
        Users savedUser = userRepository.save(users);

        return new UserJoinDTO(savedUser.getUserid());
    }

    @Override
    public UserLoginDTO userLogin(UserLoginDTO userLoginDTO) {
        Users users = Users.builder()
                .userid(userLoginDTO.getUserId())
                .password(userLoginDTO.getPassword())
                .build();

        Users loginUser = userRepository.findByUserid(userLoginDTO.getUserId());

        return new UserLoginDTO(loginUser.getUserid());
    }

}
