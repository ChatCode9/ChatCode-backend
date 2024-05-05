package com.chatcode.controller.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class PostRequestDTO {
    @Schema(description = "제목", example = "제목")
    private String title;
    @Schema(description = "내용", example = "내용")
    private String content;
    @Schema(description = "작성자", example = "홍길동")
    private String author;
    @Schema(description = "비밀번호", example = "비밀번호")
    private String password;
}
