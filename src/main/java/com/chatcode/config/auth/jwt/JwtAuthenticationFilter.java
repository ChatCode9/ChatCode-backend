package com.chatcode.config.auth.jwt;

import com.chatcode.config.auth.LoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private final ExternalProperties externalProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = getAccessToken(request);
        if (jwtProvider.validateToken(accessToken)) {
            log.info("JwtAuthenticationFilter : 토큰 유효함");
            setAuthentication(accessToken);
        } else {
            // TODO: If token is expired or invalid, reissue the refreshToken
            log.info("JwtAuthenticationFilter : 토큰 만료 또는 유효하지 않음");
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(HttpServletRequest request) {
        String accessToken = request.getHeader(externalProperties.getAccessKey());
        if (accessToken != null && accessToken.startsWith(externalProperties.getTokenPrefix())) {
            return accessToken.replace(externalProperties.getTokenPrefix(), "");
        }
        return null;
    }

    private void setAuthentication(String accessToken) {
        LoginUser loginUser = jwtProvider.getLoginUser(accessToken);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,
                accessToken, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
