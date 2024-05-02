package com.chatcode.config.auth;

import com.chatcode.jooq.tables.pojos.User;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Builder
@AllArgsConstructor
public class LoginUser implements OAuth2User {

    private final Long id;
    private final Long avatarId;
    private final String username;
    private final Map<String, Object> attributes;
    private final List<String> roles;

    public LoginUser(User user, Map<String, Object> attributes, List<String> roles) {
        this.id = user.getId();
        this.avatarId = user.getAvatarId();
        this.username = user.getUsername();
        this.attributes = attributes;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}
