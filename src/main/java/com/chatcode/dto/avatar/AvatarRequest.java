package com.chatcode.dto.avatar;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class AvatarRequest {

    @Data
    @Schema
    public static class AvatarCreateRequest {
        @Schema(description = "활동 점수", example = "100")
        @NotNull
        private Integer activityPoint;

        @Schema(description = "닉네임", example = "사용자 닉네임")
        @NotNull
        @Size(min = 1, max = 20, message = "닉네임은 1자 이상 20자 이하로 입력해주세요.")
        private String nickname;

        @Schema(description = "프로필 사진 URL", example = "http://example.com/avatar.jpg")
        @NotNull
        private String picture;

        @Schema(description = "자기소개", example = "안녕하세요")
        @Size(max = 1000, message = "자기소개는 1000자 이하로 입력해주세요.")
        private String content;
    }

    @Data
    @Schema
    public static class AvatarUpdateRequest {
        @Schema(description = "닉네임", example = "사용자 닉네임")
        @Size(min = 1, max = 20, message = "닉네임은 1자 이상 20자 이하로 입력해주세요.")
        private String nickname;

        @Schema(description = "프로필 사진 URL", example = "http://example.com/avatar.jpg")
        private String picture;

        @Schema(description = "자기소개", example = "안녕하세요")
        @Size(max = 1000, message = "자기소개는 1000자 이하로 입력해주세요.")
        private String content;
    }
}
