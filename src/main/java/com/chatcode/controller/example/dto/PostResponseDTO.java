package com.chatcode.controller.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class PostResponseDTO {
    @Schema(description = "게시글 번호", example = "1")
    private Long id;
    @Schema(description = "제목", example = "제목")
    private String title;
    @Schema(description = "내용", example = "내용")
    private String content;
    @Schema(description = "작성자", example = "홍길동")
    private String author;
    @Schema(description = "작성일", example = "2021-01-01")
    private String createdDate;
    @Schema(description = "수정일", example = "2021-01-01")
    private String modifiedDate;
}