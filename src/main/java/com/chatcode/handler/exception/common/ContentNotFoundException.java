package com.chatcode.handler.exception.common;


import com.chatcode.handler.exception.ExceptionCode;

public class ContentNotFoundException extends NotFoundException {
    public ContentNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ContentNotFoundException(ExceptionCode exceptionCode, Object data) {
        super(exceptionCode, data);
    }
}
