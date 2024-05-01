package com.chatcode.config;

import com.chatcode.config.auth.jwt.JwtAuthenticationFilter;
import com.chatcode.config.auth.jwt.JwtExceptionFilter;
import com.chatcode.config.auth.oauth.LoginOauth2UserService;
import com.chatcode.config.auth.oauth.OAuth2SuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

    private final LoginOauth2UserService loginOauth2UserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/css/**", "/js/**", "/img/**", "/lib/**")
                .requestMatchers("/favicon.ico", "/error");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.cors(cors -> cors.configurationSource(configurationSource()));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.headers(header -> header
                .frameOptions(frameOption -> frameOption.disable()));

        http.httpBasic(httpBasic -> httpBasic.disable());
        http.formLogin(form -> form.disable());
        http.logout(logout -> logout.disable());

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/login/**", "/oauth2/redirect", "/user/**").permitAll()
                .requestMatchers("/api/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated());

        http.oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo.userService(loginOauth2UserService))
                .successHandler(oAuth2SuccessHandler));

        http.with(new CustomSecurityFilterManager(), dsl -> dsl.flag(true));

        http.exceptionHandling(e -> e.authenticationEntryPoint((request, response, authException) -> {
            CustomResponseUtil.fail(response, "로그인을 진행해주세요.", HttpStatus.UNAUTHORIZED);
        }));

        http.exceptionHandling(e -> e.accessDeniedHandler((request, response, accessDeniedException) -> {
            log.error("Access Denied Handler: {}", accessDeniedException.getMessage());
            CustomResponseUtil.fail(response, "접근 권한이 없습니다.", HttpStatus.FORBIDDEN);
        }));

        return http.build();
    }

    private CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        private boolean flag;

        @Override
        public void configure(HttpSecurity builder) throws Exception {

            builder.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
            builder.addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class);

            super.configure(builder);
        }

        public CustomSecurityFilterManager flag(boolean value) {
            this.flag = value;
            return this;
        }
    }

    // TODO: move this to another class
    public class CustomResponseUtil {

        public static void fail(HttpServletResponse response, String msg, HttpStatus httpStatus) {
            try {
                ObjectMapper om = new ObjectMapper();
                Map<String, Object> responseDto = Map.of(
                        "code", -1,
                        "msg", msg,
                        "statusCode", httpStatus.value()
                );
                String responseBody = om.writeValueAsString(responseDto);

                response.setContentType("application/json; charset=utf-8");
                response.setStatus(httpStatus.value());
                response.getWriter().println(responseBody);
            } catch (Exception e) {
                log.error("서버 파싱 에러");
            }
        }
    }
}
