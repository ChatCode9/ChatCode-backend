package com.chatcode.config.auth.oauth;

import java.util.Map;
import lombok.Builder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

@Builder
public record OAuth2UserInfo(
        String name,
        String email,
        String profile,
        String providerId,
        String provider
) {
    public static OAuth2UserInfo of(String registrationId, Map<String, Object> attributes) throws OAuth2AuthenticationException {
        return switch (registrationId) {
            case "google" -> ofGoogle(attributes);
            case "github" -> ofGithub(attributes);
            default -> throw new OAuth2AuthenticationException("Unsupported registrationId: " + registrationId);
        };
    }

    private static OAuth2UserInfo ofGoogle(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("picture"))
                .providerId((String) attributes.get("sub"))
                .provider("google")
                .build();
    }

    private static OAuth2UserInfo ofGithub(Map<String, Object> attributes) {
        return OAuth2UserInfo.builder()
                .name((String) attributes.get("login"))
                .email((String) attributes.get("email"))
                .profile((String) attributes.get("avatar_url"))
                .providerId(String.valueOf(attributes.get("id")))
                .provider("github")
                .build();
    }

    public String getUsername() {
        return provider + "_" + providerId;
    }
}
