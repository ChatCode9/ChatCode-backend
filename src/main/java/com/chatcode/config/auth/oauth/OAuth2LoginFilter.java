package com.chatcode.config.auth.oauth;

import com.chatcode.config.auth.oauth.util.HttpUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class OAuth2LoginFilter extends HttpFilter implements Filter {

    private static final String QUERY_KEY = "url";
    private static final String COOKIE_KEY = "redirectUrl";
    private static final String COOKIE_PATH = "/";
    private static final int COOKIE_MAX_AGE = 60 * 3;
    private static final String PREFIX_URL = "/login/oauth2/";
    private static final String OAUTH2_AUTHORIZATION_URL = "/oauth2/authorization/";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        if (requestURI.startsWith(PREFIX_URL)) {

            String providerId = getProviderId(request);
            String redirectUrl = getRedirectUrl(request);

            setRedirectUrlOnCookie(response, redirectUrl);

            response.sendRedirect(OAUTH2_AUTHORIZATION_URL + providerId);
        }
        chain.doFilter(request, response);
    }

    private void setRedirectUrlOnCookie(HttpServletResponse response, String redirectUrl) {
        HttpUtils.setCookie(response, COOKIE_KEY, redirectUrl, COOKIE_PATH, COOKIE_MAX_AGE);
    }

    private String getProviderId(HttpServletRequest request) {
        return request.getRequestURI().substring(PREFIX_URL.length());
    }

    private String getRedirectUrl(HttpServletRequest request) {
        String redirectUrl = HttpUtils.getQueryValue(request, QUERY_KEY);
        return redirectUrl != null ? redirectUrl : "/";
    }
}
