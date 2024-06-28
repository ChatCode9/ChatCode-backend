package com.chatcode.exception.category;

import com.chatcode.exception.ExceptionCode;

public class CategoryOrderException extends CategoryException {
    public CategoryOrderException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public CategoryOrderException(ExceptionCode exceptionCode, Object data) {
        super(exceptionCode, data);
    }
}
