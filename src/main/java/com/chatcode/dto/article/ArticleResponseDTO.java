package com.chatcode.dto.article;

import com.chatcode.domain.article.ArticleVo;
import com.chatcode.domain.article.StatusType;
import com.chatcode.domain.entity.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;


    public static ArticleResponseDTO of(final ArticleVo vo){
        return ArticleResponseDTO.builder()
                .id(vo.getId())
                .dateCreated(vo.getDateCreated())
                .profileImg(vo.getPicture())
                .nickname(vo.getNickname())
                .title(vo.getTitle())
                .content(vo.getText())
                .tags(List.of(vo.getTagString().split(",")))
                .status(StatusType.of(vo.getEnabled()).name())
                .bookmark(false)
                .viewCount(vo.getViewCount())
                .commentCount(vo.getCommentCount())
                .likeCount(vo.getLikeCount())
                .build();
    }

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
