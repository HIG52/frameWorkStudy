package org.example.frameworkstudy.service;

import org.example.frameworkstudy.dto.UserJoinDTO;
import org.example.frameworkstudy.entity.Users;
import org.example.frameworkstudy.exception.InvalidUserInputException;
import org.example.frameworkstudy.repository.UserRepository;
import org.example.frameworkstudy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository; //믿음으로 패스

    @InjectMocks
    private UserServiceImpl userServiceImpl;


    private final String testId = "testid";
    private final String testPassword = "testpassword";
    private final String testName = "testname";

    @Test
    @DisplayName("회원가입 했을때 true를 반환한다.")
    void userJoinTest(){
        //given
        UserJoinDTO userJoinDTO = new UserJoinDTO();
        userJoinDTO.setUserId(testId);
        userJoinDTO.setPassword(testPassword);
        userJoinDTO.setName(testName);

        Users users = new Users();
        users.setUserid(userJoinDTO.getUserId());
        users.setName(userJoinDTO.getName());
        users.setPassword(userJoinDTO.getPassword());

        given(passwordEncoder.encode("123456")).willReturn("123456");
        given(userRepository.save(any(Users.class))).willReturn(users);

        //when
        Boolean result = userServiceImpl.userJoin(userJoinDTO);

        //then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("회원가입시 userId가 없을경우 InvalidUserInputException 반환")
    void noUserIdJoinTest(){
        //given
        UserJoinDTO userJoinDTO = new UserJoinDTO();
        userJoinDTO.setUserId("");
        userJoinDTO.setPassword(testPassword);
        userJoinDTO.setName(testName);

        Users users = new Users();
        users.setUserid(userJoinDTO.getUserId());
        users.setName(userJoinDTO.getName());
        users.setPassword(userJoinDTO.getPassword());

        //when / then
        assertThatThrownBy(() -> userServiceImpl.userJoin(userJoinDTO))
                .isInstanceOf(InvalidUserInputException.class)
                .hasMessageContaining("사용자 ID는 필수입니다");

    }

    @Test
    @DisplayName("회원가입시 password가 없을경우 InvalidUserInputException 반환")
    void noPasswordJoinTest(){
        //given
        UserJoinDTO userJoinDTO = new UserJoinDTO();
        userJoinDTO.setUserId(testId);
        userJoinDTO.setPassword("");
        userJoinDTO.setName(testName);

        Users users = new Users();
        users.setUserid(userJoinDTO.getUserId());
        users.setName(userJoinDTO.getName());
        users.setPassword(userJoinDTO.getPassword());

        //when / then
        assertThatThrownBy(() -> userServiceImpl.userJoin(userJoinDTO))
                .isInstanceOf(InvalidUserInputException.class)
                .hasMessageContaining("사용자 Password는 필수입니다");

    }

    @Test
    @DisplayName("회원가입시 name가 없을경우 InvalidUserInputException 반환")
    void noNameJoinTest(){
        //given
        UserJoinDTO userJoinDTO = new UserJoinDTO();
        userJoinDTO.setUserId(testId);
        userJoinDTO.setPassword(testPassword);
        userJoinDTO.setName("");

        Users users = new Users();
        users.setUserid(userJoinDTO.getUserId());
        users.setName(userJoinDTO.getName());
        users.setPassword(userJoinDTO.getPassword());

        //when / then
        assertThatThrownBy(
                () -> userServiceImpl.userJoin(userJoinDTO))
                .isInstanceOf(InvalidUserInputException.class)
                .hasMessageContaining("사용자 Name는 필수입니다");

    }

}
