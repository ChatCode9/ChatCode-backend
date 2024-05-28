package com.chatcode.config.auth.oauth;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginFilter extends HttpFilter implements Filter {

    private static final String QUERY_PARAM_URL = "url";
    private static final String LOGIN_COOKIE_KEY = "redirectUrl";
    private static final String COOKIE_PATH = "/";
    private static final int COOKIE_MAX_AGE = 60 * 3;
    private static final String REQUEST_URL = "/login/oauth2/";
    private static final String OAUTH2_AUTHORIZATION_URL = "/oauth2/authorization/";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith(REQUEST_URL)) {

            String providerId = request.getRequestURI().substring(REQUEST_URL.length());
            String redirectUrl = getRedirectUrl(request);

            setRedirectUrlOnCookie(response, redirectUrl);

            response.sendRedirect(OAUTH2_AUTHORIZATION_URL + providerId);
        }
        chain.doFilter(request, response);
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String redirectUrl = request.getParameter(QUERY_PARAM_URL);
        if (redirectUrl == null) {
            redirectUrl = "/";
        }
        return redirectUrl;
    }

    private void setRedirectUrlOnCookie(HttpServletResponse response, String redirectUrl) {
        Cookie cookie = new Cookie(LOGIN_COOKIE_KEY, redirectUrl);
        cookie.setHttpOnly(true);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(COOKIE_MAX_AGE);
        response.addCookie(cookie);
    }
}
