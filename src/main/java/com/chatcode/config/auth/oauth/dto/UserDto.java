package com.chatcode.config.auth.oauth.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record UserDto(
        Long avatarId,
        Long version,
        String createIp,
        String username,
        Integer status,
        List<String> roles
) {
}
