package com.chatcode.exception;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.exception.common.ResourceNotFoundException;
import com.chatcode.exception.file.EmptyImageFileException;
import com.chatcode.exception.file.ImageFileUploadException;
import com.chatcode.exception.reaction.AlreadyReactException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AlreadyReactException.class)
  public ResponseEntity<BaseResponseDto<Void>> handleAlreadyReactException(AlreadyReactException ex) {
    //Todo 에러 코드 정하기
    return ResponseEntity.badRequest().body(new BaseResponseDto<>(HttpStatus.BAD_REQUEST.value(), null ,ex.getMessage()));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<BaseResponseDto<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
    return ResponseEntity.badRequest().body(new BaseResponseDto<>(HttpStatus.BAD_REQUEST.value(), null ,ex.getMessage()));
  }

  @ExceptionHandler(EmptyImageFileException.class)
  public ResponseEntity<BaseResponseDto<Void>> handleEmptyImageFileException(EmptyImageFileException ex) {
    return ResponseEntity.badRequest().body(new BaseResponseDto<>(HttpStatus.BAD_REQUEST.value(), null ,ex.getMessage()));
  }
  @ExceptionHandler(ImageFileUploadException.class)
  public ResponseEntity<BaseResponseDto<Void>> handleImageFileUploadException(ImageFileUploadException ex) {
    return ResponseEntity.badRequest().body(new BaseResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null ,ex.getMessage()));
  }
}
