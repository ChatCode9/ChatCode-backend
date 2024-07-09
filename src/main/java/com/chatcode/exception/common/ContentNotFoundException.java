package com.chatcode.exception.common;

import com.chatcode.exception.ExceptionCode;

public class ContentNotFoundException extends NotFoundException {
    public ContentNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ContentNotFoundException(ExceptionCode exceptionCode, Object data) {
        super(exceptionCode, data);
    }
}
