package com.chatcode.dto.avatar;

import com.chatcode.domain.entity.Avatar;
import io.swagger.v3.oas.annotations.media.Schema;
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

    public static AvatarResponse of(Avatar avatar) {
        AvatarResponse response = new AvatarResponse();
        response.id = avatar.getId();
        response.activityPoint = avatar.getActivityPoint();
        response.nickname = avatar.getNickname();
        response.picture = avatar.getPicture();
        return response;
    }
}
