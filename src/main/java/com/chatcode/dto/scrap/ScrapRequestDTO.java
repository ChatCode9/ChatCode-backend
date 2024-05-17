package com.chatcode.dto.scrap;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapRequestDTO {

    @Schema(description = "스크랩한 아바타 ID", example = "1")
    @NotNull
    private Long avatarId;

    @Schema(description = "스크랩한 Article Id (게시물 Id)", example = "1")
    @NotNull
    private Long articleId;

    @Schema(description = "버전", example = "")
    @NotNull
    @Version
    private int version;
}