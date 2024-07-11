package com.chatcode.handler.exception.common;


import com.chatcode.handler.exception.ExceptionCode;

public class ResourceNotFoundException extends NotFoundException {
    public ResourceNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ResourceNotFoundException(ExceptionCode exceptionCode, Object data) {
        super(exceptionCode, data);
    }
}