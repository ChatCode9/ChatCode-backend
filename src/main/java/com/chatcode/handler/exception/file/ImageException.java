package com.chatcode.handler.exception.file;

import com.chatcode.handler.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class ImageException extends RuntimeException {
    private final int code;
    private final String message;
    private final Object data;

    public ImageException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = null;
    }

    public ImageException(final ExceptionCode exceptionCode, Object data) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = data;
    }
}
