package org.example.frameworkstudy.user;

import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.dto.UserLoginDTO;
import org.example.frameworkstudy.entity.Users;
import org.example.frameworkstudy.repository.UserRepository;
import org.example.frameworkstudy.security.JwtTokenProvider;
import org.example.frameworkstudy.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @DisplayName("회원가입 테스트")
    @Test
    void testJoinUser(){
        // given : 테스트 준비
        UserJoinDTO userJoinDTO = new UserJoinDTO("testid1", "이름4", "test!@#123");
        Users user = new Users(userJoinDTO.getUserId(), userJoinDTO.getName(), userJoinDTO.getPassword());

        //Mockito는 실제 데이터베이스에 접근하지 않도록 userRepository를 Mock 객체로 설정합니다.
        //thenReturn(user): save 메서드가 호출되면, 미리 준비한 user 객체를 반환하도록 정의합니다.
        // 이렇게 함으로써 데이터베이스 접근 없이도 로직을 테스트할 수 있습니다.
        Mockito.when(userRepository.save(Mockito.any(Users.class))).thenReturn(user);

        // when : 테스트 대상 메서드 호출
        UserJoinDTO createdUser = userService.userJoin(userJoinDTO);

        // then : 결과 검증
        //assertNotNull(createdUser): createdUser가 null이 아닌지를 확인합니다. 즉, 사용자 생성이 실패하지 않았음을 검증합니다.
        assertNotNull(createdUser);
        //assertEquals("이름4", createdUser.getName()): 사용자가 입력한 이름이 "이름4"인지 확인합니다.
        // 즉, 사용자 가입 시 입력한 정보가 올바르게 처리되었는지를 검증합니다.
        assertEquals("testid1", createdUser.getUserId());
        assertNotEquals("test!@#123", createdUser.getPassword());
    }

    @Test
    void testLoginUser(){
        UserLoginDTO userLoginDTO = new UserLoginDTO("testid1", "test!@#123");
        Users user = new Users(userLoginDTO.getUserId(), "", userLoginDTO.getPassword());

        Mockito.when(userRepository.findByUserid(userLoginDTO.getUserId())).thenReturn(user);
        Mockito.when(jwtTokenProvider.createToken(userLoginDTO.getUserId())).thenReturn("mockJwtToken");

        String userJwt = userService.userLogin(userLoginDTO);

        assertNotNull(userJwt);
        assertEquals("mockJwtToken", userJwt);

    }


}
