package com.chatcode.dto.article;

import com.chatcode.domain.article.ArticleVo;
import com.chatcode.domain.article.StatusType;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetailResponseDto {
    private Long id;
    private LocalDateTime timeline;
    private String title;
    private String content;
    private List<String> tags;
    private String status;
    private Boolean bookmark;
    private Integer viewCount;
    private Integer likeCount;
    private Boolean isLiked;

    public static ArticleDetailResponseDto of(ArticleVo vo, Boolean isLiked) {
        return ArticleDetailResponseDto.builder()
                .id(vo.getId())
                .timeline(vo.getDateCreated())
                .title(vo.getTitle())
                .content(vo.getText())
                .tags(Arrays.stream(vo.getTagString().split(",")).toList())
                .status(StatusType.of(vo.getEnabled()).name())
                .viewCount(vo.getViewCount())
                .likeCount(vo.getLikeCount())
                .isLiked(isLiked)
                .bookmark(false)
                .build();
    }
}
