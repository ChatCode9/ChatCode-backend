package com.chatcode.config;

import com.chatcode.config.auth.jwt.JwtAuthenticationFilter;
import com.chatcode.config.auth.jwt.JwtExceptionFilter;
import com.chatcode.config.auth.jwt.JwtProvider;
import com.chatcode.config.auth.jwt.LoginUserAuthentication;
import com.chatcode.config.auth.oauth.OAuth2LoginFilter;
import com.chatcode.config.auth.oauth.OAuth2LoginUserService;
import com.chatcode.config.auth.oauth.OAuth2SuccessHandler;
import com.chatcode.dto.BaseResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfig {

    private final OAuth2LoginUserService oAuth2LoginUserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final OAuth2LoginFilter oAuth2LoginFilter;
    private final JwtProvider jwtProvider; // TODO: remove this when deploying

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**")
                .requestMatchers("/favicon.ico", "/error");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<OAuth2LoginFilter> oauth2LoginFilter() {
        FilterRegistrationBean<OAuth2LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(oAuth2LoginFilter);
        registrationBean.addUrlPatterns("/login/oauth2/*");
        return registrationBean;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // TODO: remove this when deploying
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();

        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.configurationSource(configurationSource()));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.headers(header -> header
                .frameOptions(frameOption -> frameOption.disable()));

        http.httpBasic(httpBasic -> httpBasic.disable());
        http.formLogin(form -> form.disable());
        http.logout(logout -> logout.disable());

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll());

        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2LoginUserService))
                .successHandler(oAuth2SuccessHandler));

        // TODO: remove this when deploying
        http.addFilter(new LoginUserAuthentication(authenticationManager, jwtProvider));
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class);

        http.exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> {
            failedResponse(response, "로그인을 진행해주세요.", HttpStatus.UNAUTHORIZED);
        }));

        http.exceptionHandling(e -> e.accessDeniedHandler((request, response, accessDeniedException) -> {
            log.error("Access Denied Handler: {}", accessDeniedException.getMessage());
            failedResponse(response, "접근 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }));

        return http.build();
    }

    private CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // TODO: change to specific domain
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private void failedResponse(HttpServletResponse response, String msg, HttpStatus httpStatus) {
        try {
            ObjectMapper om = new ObjectMapper();
            BaseResponseDto<?> responseDto = new BaseResponseDto<>(-1, null, msg);
            String responseBody = om.writeValueAsString(responseDto);

            response.setContentType("application/json; charset=utf-8");
            response.setStatus(httpStatus.value());
            response.getWriter().println(responseBody);
        } catch (Exception e) {
            log.error("Server Parsing Error: {}", e.getMessage());
        }
    }
}
