package com.chatcode.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ArticleResponseDTO {

    @ToString
    @Builder
    @Getter
    @Setter
    public static class ArticleCreateResponseDTO {
        private Long id;
        private String contentText;
        private String title;
        private Long version;
    }
}
