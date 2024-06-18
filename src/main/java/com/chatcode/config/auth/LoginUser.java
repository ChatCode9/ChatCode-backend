package com.chatcode.config.auth;

import com.chatcode.config.auth.enums.Status;
import com.chatcode.domain.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Builder
@AllArgsConstructor
public class LoginUser implements OAuth2User, UserDetails {

    private final Long id;
    private final Long avatarId;
    private final String username;
    private final Map<String, Object> attributes;
    private final List<String> roles;
    private final Status status;

    public LoginUser(User user, Map<String, Object> attributes, List<String> roles) {
        this.id = user.getId();
        this.avatarId = user.getAvatar().getId();
        this.username = user.getUsername();
        this.attributes = attributes;
        this.roles = roles;
        this.status = user.getStatus();
    }

    public Long getId() {
        return id;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public Integer getStatus() {
        return status.getValue();
    }

    // OAuth2User
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
    // UserDetails
    @Override
    public String getPassword() {
        // TODO: remove this password after deployment
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode("1234");
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
