package com.chatcode.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

public class ArticleResponseDto {


    @Data
    @Builder
    public static class ArticleCreateResponseDTO {
        @Schema(description = "Article Id", example = "1")
        private Long id;

        @Schema(description = "Article 내용", example = "안녕하세요")
        private String contentText;

        @Schema(description = "Article 제목", example = "안녕하세요")
        private String title;

        @Schema(description = "Article 태그", example = "java spring 프론트엔드")
        private String tag;

        private Long version;
    }
}
