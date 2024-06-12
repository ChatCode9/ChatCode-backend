package com.chatcode.config.auth.jwt;

import com.chatcode.config.auth.LoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final JwtSettings jwtSettings;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = getAccessToken(request);
        if (jwtProvider.validateToken(accessToken)) {
            setAuthentication(accessToken);
            filterChain.doFilter(request, response);
            return;
        }
        log.info("JwtAuthenticationFilter : 토큰 만료 또는 유효하지 않음, 토큰: {}", accessToken);
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        String accessToken = fetchCookie(request, jwtSettings.getAccessKey());
        if (accessToken != null && accessToken.startsWith(jwtSettings.getTokenPrefix())) {
            return accessToken.replace(jwtSettings.getTokenPrefix(), "");
        }
        return request.getHeader(jwtSettings.getAccessKey());
    }

    private void setAuthentication(String accessToken) {
        LoginUser loginUser = jwtProvider.getLoginUser(accessToken);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                accessToken, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String fetchCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> key.equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }
}
