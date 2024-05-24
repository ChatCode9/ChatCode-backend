package com.chatcode.dto;

import com.chatcode.domain.entity.Scrap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapResponseDto {
    @Schema(description = "게시물 제목", example = "안녕하세요")
    private String articleTitle;

    @Schema(description = "스크랩한 시간", example = "2024-05-14T18:27:02")
    private LocalDateTime dateCreated;

    public static ScrapResponseDto of(Scrap scrap) {
        ScrapResponseDto response = new ScrapResponseDto();
        response.articleTitle = scrap.getArticle().getTitle();
        response.dateCreated = scrap.getDateCreated();
        return response;
    }
}