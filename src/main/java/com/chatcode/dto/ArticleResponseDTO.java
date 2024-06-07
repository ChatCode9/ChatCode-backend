package com.chatcode.dto;

import lombok.Builder;
import lombok.Data;

public class ArticleResponseDTO {


    @Data
    @Builder
    public static class ArticleCreateResponseDTO {
        private Long id;
        private String contentText;
        private String title;
        private String tag;
        private Long version;
    }
}
