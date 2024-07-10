package com.chatcode.handler.exception.common;

import com.chatcode.handler.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final int code;
    private final String message;
    private final Object data;

    public NotFoundException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = null;
    }

    public NotFoundException(final ExceptionCode exceptionCode, Object data) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = data;
    }
}
