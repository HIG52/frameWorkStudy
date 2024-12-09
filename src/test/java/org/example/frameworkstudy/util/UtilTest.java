package org.example.frameworkstudy.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("암호화 했을때 암호화된 비밀번호 반환")
    public void passwordEncordTest(){
        /*//given
        String beforePassword = "123456";
        String afterPassword;

        when(passwordEncoder.encode(beforePassword)).thenReturn(afterPassword);
        when(passwordEncoder.matches(afterPassword, afterPassword)).thenReturn(true);

        //when (항해 아이돌)
        String whenPassword = passwordEncoder.encode(beforePassword);

        assertThat(passwordEncoder.matches(beforePassword, whenPassword)).isEqualTo(true);*/
        String beforePassword = "123456";
        String afterPassword = "132456";
        verify(passwordEncoder).encode(beforePassword);
        verify(passwordEncoder).matches(beforePassword, afterPassword);
    }

}