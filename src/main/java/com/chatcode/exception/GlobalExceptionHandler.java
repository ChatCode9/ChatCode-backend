package com.chatcode.exception;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.exception.category.CategoryException;
import com.chatcode.exception.common.NotFoundException;
import com.chatcode.exception.file.ImageException;
import com.chatcode.exception.reaction.ReactException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.chatcode.exception.ExceptionCode.INTERNAL_SEVER_ERROR;

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
}
