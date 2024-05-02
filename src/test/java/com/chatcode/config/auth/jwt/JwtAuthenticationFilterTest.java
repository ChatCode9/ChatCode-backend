package com.chatcode.config.auth.jwt;

import com.chatcode.config.auth.LoginUser;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class JwtAuthenticationFilterTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private ExternalProperties externalProperties;

    @Test
    void authorization_success_test() throws Exception {
        // given
        LoginUser loginUser = LoginUser.builder()
                .id(1L)
                .roles(List.of("ROLE_USER", "ROLE_ADMIN"))
                .avatarId(1L)
                .username("test")
                .build();
        String accessToken = jwtProvider.createAccessToken(loginUser);

        // when
        ResultActions resultActions = mvc.perform(get("/api").header(externalProperties.getAccessKey(), accessToken));

        // then
        resultActions.andExpect(status().isNotFound()); // 404 (권한 인증은 통과 된 상태)
    }

    @Test
    void authorization_fail_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/api"));

        // then
        resultActions.andExpect(status().isUnauthorized()); // 401 (권한 인증 실패 상태)
    }

    @Test
    void authorization_admin_test() throws Exception {
        // given
        LoginUser loginUser = LoginUser.builder()
                .id(1L)
                .roles(List.of("ROLE_USER"))
                .avatarId(1L)
                .username("test")
                .build();
        String accessToken = jwtProvider.createAccessToken(loginUser);

        // when
        ResultActions resultActions = mvc.perform(get("/admin").header(externalProperties.getAccessKey(), accessToken));

        // then
        resultActions.andExpect(status().isForbidden()); // 403 (접근 권한 없음)
    }
}