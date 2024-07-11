package com.chatcode.handler.exception;

import static com.chatcode.handler.exception.ExceptionCode.INTERNAL_SEVER_ERROR;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.handler.exception.category.CategoryException;
import com.chatcode.handler.exception.common.NotFoundException;
import com.chatcode.handler.exception.dto.DtoValidationException;
import com.chatcode.handler.exception.file.ImageException;
import com.chatcode.handler.exception.reaction.ReactException;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<BaseResponseDto<Object>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.badRequest().body(new BaseResponseDto<>(ex.getCode(), ex.getData(), ex.getMessage()));
    }

    @ExceptionHandler(ReactException.class)
    public ResponseEntity<BaseResponseDto<Object>> handleAlreadyReactException(ReactException ex) {
        return ResponseEntity.badRequest().body(new BaseResponseDto<>(ex.getCode(), ex.getData(), ex.getMessage()));
    }

    @ExceptionHandler(ImageException.class)
    public ResponseEntity<BaseResponseDto<Object>> handleImageException(ImageException ex) {
        return ResponseEntity.badRequest().body(new BaseResponseDto<>(ex.getCode(), ex.getData(), ex.getMessage()));
    }

    @ExceptionHandler(CategoryException.class)
    public ResponseEntity<BaseResponseDto<Object>> handleCategoryException(CategoryException ex) {
        return ResponseEntity.badRequest().body(new BaseResponseDto<>(ex.getCode(), ex.getData(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDto<Void>> handleException(Exception ex) {
        return ResponseEntity.badRequest()
                .body(new BaseResponseDto<>(INTERNAL_SEVER_ERROR.getCode(), null, INTERNAL_SEVER_ERROR.getMessage()));
    }

    @ExceptionHandler(DtoValidationException.class)
    public ResponseEntity<BaseResponseDto<Map<String, String>>> handleDtoValidationException(
            DtoValidationException ex) {
        return ResponseEntity.badRequest()
                .body(new BaseResponseDto<>(HttpStatus.BAD_REQUEST.value(), ex.getErrorMap(), ex.getMessage()));
    }
}
