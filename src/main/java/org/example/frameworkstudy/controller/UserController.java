package org.example.frameworkstudy.controller;

import jakarta.servlet.http.HttpSession;
import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.dto.UserLoginDTO;
import org.example.frameworkstudy.security.JwtTokenUtil;
import org.example.frameworkstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입
    @PostMapping("/user")
    public ResponseEntity<UserJoinDTO> joinUser(@RequestBody UserJoinDTO userJoinDTO) {
        UserJoinDTO joinUser = userService.userJoin(userJoinDTO);
        return new ResponseEntity<>(joinUser, HttpStatus.CREATED);
    }
    
    //로그인
    @PostMapping("/user/login")
    public String login(@RequestBody UserLoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserId(), loginDTO.getPassword())
        );

        return JwtTokenUtil.generateToken(loginDTO.getUserId());
    }

}
