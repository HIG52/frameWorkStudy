package org.example.frameworkstudy.controller;

import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/user")
    public ResponseEntity<Boolean> joinUser(@RequestBody UserJoinDTO userJoinDTO) {
        boolean joinResult = userService.userJoin(userJoinDTO);
        return new ResponseEntity<>(joinResult, HttpStatus.CREATED);
    }

}
