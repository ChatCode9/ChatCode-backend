package com.chatcode.dto;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Scrap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapResponseDTO {
    @Schema(description = "스크랩한 아바타 ID", example = "1")
    private Long avatarId;

    @Schema(description = "스크랩한 Article Id (게시물 Id)", example = "1")
    private Long articleId;

    @Schema(description = "게시물 제목", example = "안녕하세요")
    private String articleTitle;

    @Schema(description = "스크랩한 시간", example = "2024-05-14T18:27:02")
    private LocalDateTime dateCreated;

    public static ScrapResponseDTO of(Scrap scrap, Article article, Avatar avatar) {
        ScrapResponseDTO response = new ScrapResponseDTO();
        response.avatarId = avatar.getId();
        response.articleId = article.getId();
        response.articleTitle = article.getTitle();
        response.dateCreated = scrap.getDateCreated();
        return response;
    }
}