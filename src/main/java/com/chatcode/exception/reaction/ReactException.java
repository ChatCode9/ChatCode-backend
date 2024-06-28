package com.chatcode.exception.reaction;

import com.chatcode.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ReactException extends RuntimeException {
    private final int code;
    private final String message;
    private final Object data;

    public ReactException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = null;
    }

    public ReactException(final ExceptionCode exceptionCode, Object data) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = data;
    }
}
