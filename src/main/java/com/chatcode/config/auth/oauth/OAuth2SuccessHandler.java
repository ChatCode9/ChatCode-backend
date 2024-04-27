package com.chatcode.config.auth.oauth;

import jakarta.servlet.ServletException;
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

    private static final String REDIRECT_URL = "/oauth2/redirect";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("AuthenticationSuccessHandler 성공");
        // TODO: Generate JWT token
        // TODO: token to header, not the parameter
        String redirectUrl = UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParam("accessToken", "TEST_ACCESS_TOKEN")
                .build().toUriString();

        response.sendRedirect(redirectUrl);
    }
}
