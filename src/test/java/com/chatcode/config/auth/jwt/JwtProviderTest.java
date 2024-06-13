package com.chatcode.config.auth.jwt;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.chatcode.config.auth.LoginUser;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class JwtProviderTest {

    @Autowired
    private JwtSettings jwtSettings;
    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void createToken_test() {
        // given
        LoginUser loginUser = LoginUser.builder()
                .id(1L)
                .roles(List.of("ROLE_USER"))
                .avatarId(1L)
                .username("test")
                .build();

        // when
        String token = jwtProvider.createAccessToken(loginUser);
        System.out.println("token = " + token);

        // then
        assertTrue(token.startsWith(jwtSettings.getTokenPrefix()));
    }

    @Test
    void verify_test() {
        // given
        String accessToken = createAccessToken();

        // when
        if (jwtProvider.validateToken(accessToken) == false) {
            throw new RuntimeException("토큰이 유효하지 않습니다.");
        }
        LoginUser loginUser = jwtProvider.getLoginUser(accessToken);

        // then
        Assertions.assertEquals(1L, loginUser.getId());
        Assertions.assertEquals(1L, loginUser.getAvatarId());
        Assertions.assertEquals("test", loginUser.getName());
    }

    @Test
    void verify_fail_test() {
        // given
        String accessToken = createAccessToken();
        accessToken = accessToken + "a";

        // when
        boolean result = jwtProvider.validateToken(accessToken);

        // then
        Assertions.assertFalse(result);
    }

    @Test
    void getLoginUser_test() {
        // given
        String accessToken = createAccessToken();

        // when
        LoginUser loginUser = jwtProvider.getLoginUser(accessToken);

        System.out.println("loginUser.getId() = " + loginUser.getId());
        System.out.println("loginUser.getName() = " + loginUser.getName());
        System.out.println("loginUser.getAuthorities().toString() = " + loginUser.getAuthorities().toString());
    }

    private String createAccessToken() {
        LoginUser loginUser = LoginUser.builder()
                .id(1L)
                .roles(List.of("ROLE_USER", "ROLE_ADMIN"))
                .avatarId(1L)
                .username("test")
                .build();

        String accessToken = jwtProvider.createAccessToken(loginUser);
        accessToken = accessToken.replace(jwtSettings.getTokenPrefix(), "");
        return accessToken;
    }
}