package com.chatcode.config.auth.oauth;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.config.auth.jwt.JwtProvider;
import com.chatcode.config.auth.jwt.JwtSettings;
import com.chatcode.config.auth.oauth.util.HttpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        LoginUser user = getLoginUser(authentication);

        String accessToken = createAccessToken(user);
        setAccessTokenOnCookie(response, accessToken);

        String redirectUrl = getRedirectUrl(request);
        String responseUrl = buildRedirectUrl(redirectUrl, user);

        response.sendRedirect(responseUrl);
    }

    private LoginUser getLoginUser(Authentication authentication) {
        return (LoginUser) authentication.getPrincipal();
    }

    private String createAccessToken(LoginUser user) {
        return jwtProvider.createAccessToken(user);
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String redirectUrl = HttpUtils.getCookieValue(request.getCookies(), REDIRECT_COOKIE_KEY);
        return redirectUrl != null ? redirectUrl : "/";
    }

    private void setAccessTokenOnCookie(HttpServletResponse response, String accessToken) {
        HttpUtils.setCookie(response, jwtSettings.getAccessKey(), accessToken, COOKIE_PATH,
                jwtSettings.getAccessTokenExpirationTime());
    }

    private String buildRedirectUrl(String redirectUrl, LoginUser user) {
        return UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("success", true)
                .queryParam("profile", user.getStatus())
                .queryParam("avatar", user.getAvatarId())
                .build().toUriString();
    }
}
