package org.example.frameworkstudy.service;

import org.example.frameworkstudy.entity.Users;
import org.example.frameworkstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // DB에서 사용자 조회
        Users userEntity = userRepository.findByUserid(userId);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + userId);
        }

        // UserDetails 객체 반환
        return User.builder()
                .username(userEntity.getUserid())
                .password(userEntity.getPassword())
                .authorities("ROLE_USER") // 예: "ROLE_USER"
                .build();
    }
}


