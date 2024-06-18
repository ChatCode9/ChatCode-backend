package com.chatcode.config.auth.oauth.dto;

public record LoginRequest(
        String username,
        String password
) {}