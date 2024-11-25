package org.example.frameworkstudy.controller;

import jakarta.servlet.http.HttpSession;
import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.dto.UserLoginDTO;
import org.example.frameworkstudy.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserJoinDTO> joinUser(@RequestBody UserJoinDTO userJoinDTO) {
        UserJoinDTO joinUser = userService.userJoin(userJoinDTO);
        return new ResponseEntity<>(joinUser, HttpStatus.CREATED);
    }
    
    //로그인
    @GetMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO loginDTO, HttpSession session) {
        UserLoginDTO loginUser = userService.userLogin(loginDTO);

        if (loginUser != null) {
            // 로그인 성공 시 세션에 사용자 ID 저장
            session.setAttribute("userId", loginUser.getUserId());
            System.out.println("Session Created: " + session.getId());
            System.out.println("Session UserId: " + session.getAttribute("userId"));
            return ResponseEntity.ok("Login Success");
        }

        // 로그인 실패 처리
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

}
