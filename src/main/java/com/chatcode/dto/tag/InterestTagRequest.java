package com.chatcode.dto.tag;

import com.chatcode.domain.entity.InterestTag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class InterestTagRequest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Schema
    public static class InterestTagIdRequest {
        @Schema(description = "태그 ID", example = "1")
        private Long id;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Schema
    public static class InterestTagNameRequest {
        @Schema(description = "태그 이름", example = "Java")
        private String name;

        public static InterestTag toEntity(InterestTagNameRequest request) {
            InterestTag tag = new InterestTag();
            tag.setName(request.getName());
            return tag;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Schema
    public static class InterestTagRenameRequest {
        @Schema(description = "태그 ID", example = "1")
        private Long id;
        @Schema(description = "태그 이름", example = "Java")
        private String name;
    }
}
