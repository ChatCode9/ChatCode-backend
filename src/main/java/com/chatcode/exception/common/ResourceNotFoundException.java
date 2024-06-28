package com.chatcode.exception.common;

import com.chatcode.exception.ExceptionCode;

public class ResourceNotFoundException extends NotFoundException {
    public ResourceNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ResourceNotFoundException(ExceptionCode exceptionCode, Object data) {
        super(exceptionCode, data);
    }
}