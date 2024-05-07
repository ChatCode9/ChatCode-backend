package com.chatcode.config.auth.oauth;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.config.auth.jwt.JwtProvider;
import com.chatcode.config.auth.jwt.JwtSettings;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${external.url.redirect}")
    private String REDIRECT_URL;
    private final JwtProvider jwtProvider;
    private final JwtSettings jwtSettings;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String accessToken = jwtProvider.createAccessToken((LoginUser) authentication.getPrincipal());

        response.addCookie(createCookie(jwtSettings.getAccessKey(), accessToken));
        response.sendRedirect(REDIRECT_URL);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtSettings.getAccessTokenExpirationTime());
        return cookie;
    }
}
