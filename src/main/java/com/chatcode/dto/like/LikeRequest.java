package com.chatcode.dto.like;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class LikeRequest {
    @Schema(description = "좋아요 유무", example = "true = 좋아요 / false = 싫어요")
    private Boolean isLike;
}
