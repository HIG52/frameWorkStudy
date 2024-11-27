package org.example.frameworkstudy.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.dto.UserLoginDTO;
import org.example.frameworkstudy.entity.Users;
import org.example.frameworkstudy.repository.UserRepository;
import org.example.frameworkstudy.security.JwtTokenProvider;
import org.example.frameworkstudy.service.UserService;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

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
    public String userLogin(UserLoginDTO userLoginDTO) {
        // 사용자 조회
        Users loginUser = userRepository.findByUserid(userLoginDTO.getUserId());

        if (loginUser == null) {
            throw new RuntimeException("ID가 존재하지 않습니다.");
        }

        // TODO : 암호화 / 비밀번호 비교
        if (!loginUser.getPassword().equals(userLoginDTO.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 생성 및 반환
        return jwtTokenProvider.createToken(loginUser.getUserid());
    }

}
