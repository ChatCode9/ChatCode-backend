package com.chatcode.dto.article;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

public class ArticleRequestDTO {

    @Data
    public static class ArticleCreateRequestDTO {
        //content
        @NotNull
        @NotEmpty
        @Size(max = 10_000, message = "최대 10,000자 까지 입력 가능합니다.")
        private String contentText;

        //article
        @Size(max = 100, message = "최대 100자 까지 입력 가능합니다.")
        @NotNull
        @NotEmpty
        private String title;

        // tags
        private List<String> tags;

        // category
        @NotNull(message = "카테고리를 선택해주세요.")
        @NotEmpty(message = "카테고리를 선택해주세요.")
        private Long categoryId;
    }

    @Data
    public static class ArticleUpdateRequestDTO {
        //content
        @NotNull
        @NotEmpty
        @Size(max = 10_000, message = "최대 10,000자 까지 입력 가능합니다.")
        private String contentText;
        private int contentId;

        //article
        @Size(max = 100, message = "최대 100자 까지 입력 가능합니다.")
        @NotNull
        @NotEmpty
        private String title;

        // tags
        private List<String> tags;

        // category
        @NotNull(message = "카테고리를 선택해주세요.")
        @NotEmpty(message = "카테고리를 선택해주세요.")
        private Long categoryId;
    }
}
