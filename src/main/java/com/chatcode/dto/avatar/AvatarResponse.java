package com.chatcode.dto.avatar;

import com.chatcode.domain.entity.Avatar;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "아바타 응답 객체")
@NoArgsConstructor
@Data
public class AvatarResponse {
    @Schema(description = "아바타 ID", example = "1")
    private Long id;

    @Schema(description = "활동 점수", example = "100")
    private Integer activityPoint;

    @Schema(description = "닉네임", example = "사용자 닉네임")
    private String nickname;

    @Schema(description = "프로필 사진 URL", example = "http://example.com/avatar.jpg")
    private String picture;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(description = "자기소개", example = "안녕하세요")
    private String content;

    public static AvatarResponse of(Avatar avatar) {
        AvatarResponse response = new AvatarResponse();
        response.id = avatar.getId();
        response.activityPoint = avatar.getActivityPoint();
        response.nickname = avatar.getNickname();
        response.picture = avatar.getPicture();
        response.content = avatar.getContent();
        return response;
    }

    public static List<AvatarResponse> of(List<Avatar> avatars) {
        return avatars.stream()
                .map(AvatarResponse::of)
                .collect(Collectors.toList());
    }
}
