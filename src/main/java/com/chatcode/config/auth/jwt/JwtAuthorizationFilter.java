package com.chatcode.config.auth.jwt;

import com.chatcode.config.auth.LoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final ExternalProperties externalProperties;
    private final JwtProvider jwtProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, ExternalProperties externalProperties,
                                  JwtProvider jwtProvider) {
        super(authenticationManager);
        this.externalProperties = externalProperties;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (hasValidToken(request)) {
            String token = request.getHeader(externalProperties.getAccessKey())
                    .replace(externalProperties.getTokenPrefix(), "");
            LoginUser loginUser = jwtProvider.getLoginUser(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null,
                    loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private boolean hasValidToken(HttpServletRequest request) {
        String header = request.getHeader(externalProperties.getAccessKey());
        return (header != null && header.startsWith(externalProperties.getTokenPrefix()));
    }
}
