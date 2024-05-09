package com.chatcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema
public class BaseResponseDto<T> {
    @Schema(description = "응답코드", example = "10001(게시글 중복 좋아요인 경우) / 10000(좋아요 요청을 한 게시글ID가 존재하지 않는 경우)")
    private int code;
    @Schema(description = "요청에 대한 응답 데이터", example = "에러가 발생한 경우 Empty String")
    private T data;
    @Schema(description = "에러에 대한 상세 메시지", example = "에러가 발생하지 않은 경우 Empty String")
    private String message;

    public BaseResponseDto(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}