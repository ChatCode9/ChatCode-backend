package com.chatcode.config.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chatcode.config.auth.LoginUser;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtProvider {

    private static final String DELIMITER = ",";
    private static final String KEY_USER = "userId";
    private static final String KEY_AVATAR = "avatarId";
    private static final String KEY_ROLE = "role";

    private final ExternalProperties externalProperties;

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC512(externalProperties.getTokenSecretKey());
    }

    public String createAccessToken(LoginUser loginUser) {
        return createToken(loginUser, externalProperties.getAccessTokenExpirationTime());
    }

    public String createRefreshToken(LoginUser loginUser) {
        return createToken(loginUser, externalProperties.getRefreshTokenExpirationTime());
    }

    private String createToken(LoginUser loginUser, Integer expirationTime) {
        String tokenPrefix = externalProperties.getTokenPrefix();
        return tokenPrefix + JWT.create()
                .withSubject(loginUser.getName())
                .withClaim(KEY_USER, loginUser.getId())
                .withClaim(KEY_AVATAR, loginUser.getAvatarId())
                .withClaim(KEY_ROLE, loginUser.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(DELIMITER)))
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(getAlgorithm());
    }

    public LoginUser getLoginUser(String jwtToken) throws JWTVerificationException {
        DecodedJWT decodedJWT = getDecodedJWT(jwtToken);
        return LoginUser.builder()
                .id(decodedJWT.getClaim(KEY_USER).asLong())
                .avatarId(decodedJWT.getClaim(KEY_AVATAR).asLong())
                .username(decodedJWT.getSubject())
                .roles(getAuthorities(decodedJWT.getClaim(KEY_ROLE).asString()))
                .build();
    }

    public boolean validateToken(String jwtToken) {
        try {
            getDecodedJWT(jwtToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private DecodedJWT getDecodedJWT(String jwtToken) throws JWTVerificationException {
        return JWT.require(getAlgorithm())
                .build()
                .verify(jwtToken);
    }

    private List<String> getAuthorities(String roles) {
        return List.of(roles.split(DELIMITER));
    }
}
