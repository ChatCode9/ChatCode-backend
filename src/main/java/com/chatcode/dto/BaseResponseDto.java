package com.chatcode.dto;

import lombok.Getter;

@Getter
public class BaseResponseDto<T> {
    private int code;
    private T data;
    private String message;

    public BaseResponseDto(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}