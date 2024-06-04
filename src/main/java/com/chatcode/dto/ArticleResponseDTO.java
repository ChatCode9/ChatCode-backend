package com.chatcode.dto;

import com.chatcode.domain.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ArticleResponseDTO {


    @Data
    @Builder
    public static class ArticleCreateResponseDTO {
        private Long id;
        private String contentText;
        private String title;
        private Long version;
    }

    public static ArticleCreateResponseDTO of(final Article article) {
        return new ArticleCreateResponseDTO(
                article.getId(),
                "",
                article.getTitle(),
                article.getVersion()
        );
    }
}
