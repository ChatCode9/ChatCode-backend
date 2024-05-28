package com.chatcode.config.auth.oauth;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.config.auth.jwt.JwtProvider;
import com.chatcode.config.auth.jwt.JwtSettings;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private static final String COOKIE_PATH = "/";
    private static final String REDIRECT_COOKIE_KEY = "redirectUrl";

    private final JwtProvider jwtProvider;
    private final JwtSettings jwtSettings;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        LoginUser user = (LoginUser) authentication.getPrincipal();
        String accessToken = jwtProvider.createAccessToken(user);
        String redirectUrl = getRedirectUrl(request);

        response.addCookie(createCookie(jwtSettings.getAccessKey(), accessToken));

        String responseUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("success", true)
                .queryParam("profile", user.getStatus())
                .build()
                .toUriString();

        response.sendRedirect(responseUrl);
    }

    private String getRedirectUrl(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
            .filter(cookie -> REDIRECT_COOKIE_KEY.equals(cookie.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElse("/");
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(jwtSettings.getAccessTokenExpirationTime());
        return cookie;
    }
}
