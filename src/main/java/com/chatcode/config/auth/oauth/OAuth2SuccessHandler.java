package com.chatcode.config.auth.oauth;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.config.auth.jwt.JwtProvider;
import com.chatcode.config.auth.jwt.JwtSettings;
import com.chatcode.config.auth.oauth.dto.OAuth2SuccessResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private static final String CONTENT_TYPE = "application/json";
    private static final String CHARSET = "UTF-8";
    private static final String COOKIE_PATH = "/";

    private final JwtProvider jwtProvider;
    private final JwtSettings jwtSettings;
    private final ObjectMapper om;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String accessToken = jwtProvider.createAccessToken(loginUser);

        response.addCookie(createCookie(jwtSettings.getAccessKey(), accessToken));

        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARSET);
        response.getWriter().write(createResponseBody(loginUser));
    }

    private String createResponseBody(LoginUser loginUser) throws JsonProcessingException {
        Boolean isProfileChanged = loginUser.getStatus() != 0;
        return om.writeValueAsString(new OAuth2SuccessResponse(
            isProfileChanged,
            loginUser.getId(),
            loginUser.getAvatarId()
        ));
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(jwtSettings.getAccessTokenExpirationTime());
        return cookie;
    }
}
