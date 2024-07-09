package com.chatcode.exception.category;

import com.chatcode.exception.ExceptionCode;
import lombok.Getter;

@Getter
public class CategoryException extends RuntimeException {
    private final int code;
    private final String message;
    private final Object data;

    public CategoryException(final ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = null;
    }

    public CategoryException(final ExceptionCode exceptionCode, Object data) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
        this.data = data;
    }
}
