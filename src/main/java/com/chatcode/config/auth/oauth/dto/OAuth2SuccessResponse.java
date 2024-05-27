package com.chatcode.config.auth.oauth.dto;

public record OAuth2SuccessResponse(
        Boolean isProfileChanged,
        Long userId,
        Long avatarId
) {
}
