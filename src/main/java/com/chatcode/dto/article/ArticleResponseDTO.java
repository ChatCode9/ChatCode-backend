package com.chatcode.dto.article;

import com.chatcode.domain.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

public class ArticleResponseDTO {
    private Long id;
    private LocalDateTime dateCreated;
    private String profileImg;
    private String nickname;
    private String title;
    private String content;
    private List<String> tags;
    private String status;
    private Boolean bookmark;
    private Boolean enabled;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;


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
