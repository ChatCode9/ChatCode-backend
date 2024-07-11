package com.chatcode.handler.exception.dto;

import java.util.Map;
import lombok.Getter;

@Getter
public class DtoValidationException extends RuntimeException {
    private Map<String, String> errorMap;

    public DtoValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }
}
