package org.example.frameworkstudy.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworkstudy.dto.UserDTO;
import org.example.frameworkstudy.entity.Users;
import org.example.frameworkstudy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO userJoin(UserDTO userDTO) {
        Users users = Users.builder()
                .userid(userDTO.getUserId())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
        Users savedUser = userRepository.save(users);

        return new UserDTO(savedUser.getUserid());
    }

    /*@Override
    public UserEntity saveEntiry(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity SaveDto(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.builder()
                .userid(userDTO.getUserId())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
        return saveEntiry(userEntity);
    }*/


}
