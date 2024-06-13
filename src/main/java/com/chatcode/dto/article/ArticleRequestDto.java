package com.chatcode.dto.article;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

public class ArticleRequestDto {

    @Data
    public static class ArticleCreateRequestDTO {
        @Schema(description = "Article 제목", example = "안녕하세요")
        @Size(max = 10)
        @NotNull
        @NotEmpty
        private String title;

        @Schema(description = "Article 내용", example = "안녕하세요")
        @NotNull
        @NotEmpty
        @Size(max = 10_000)
        private String contentText;

        @Schema(description = "Article 태그", example = "java spring 프론트엔드")
        private List<String> tags;
    }

    @Data
    public static class ArticleUpdateRequestDTO {
        @Schema(description = "Article 제목", example = "안녕하세요")
        @Size(max = 10)
        @NotNull
        @NotEmpty
        private String title;

        @Schema(description = "Article 내용", example = "안녕하세요")
        @NotNull
        @NotEmpty
        @Size(max = 10_000)
        private String contentText;

        @Schema(description = "Article 태그", example = "java spring 프론트엔드")
        private List<String> tags;
    }
}
