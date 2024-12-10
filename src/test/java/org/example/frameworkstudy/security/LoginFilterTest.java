package org.example.frameworkstudy.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.assertj.core.api.Assertions;
import org.example.frameworkstudy.service.CustomUserDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class LoginFilterTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private JWTUtil jwtUtil;

    @Mock
    private FilterChain chain;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CustomUserDetails customUserDetails;

    @InjectMocks
    private LoginFilter loginFilter;

    private final String testId = "testId";
    private final String testPassword = "testPassword";
    private final String testRole = "testRole";

    @Test
    @DisplayName("Authentication 인증 성공시 인증객체를 반환한다.")
    void attemptAuthenticationTest() {
        //given
        given(request.getParameter("username")).willReturn(testId);
        given(request.getParameter("password")).willReturn(testPassword);

        Authentication expectedAuth = mock(Authentication.class);
        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .willReturn(expectedAuth);

        //when
        Authentication result = loginFilter.attemptAuthentication(request, response);

        //then
        Assertions.assertThat(result).isSameAs(expectedAuth);
    }

    @Test
    @DisplayName(" JWT 토큰을 헤더에 담아 반환한다.")
    void successfulAuthenticationTest() {
        //given
        String userId = testId;



        //when

        //then
    }

    @Test
    void unsuccessfulAuthentication() {
    }
}