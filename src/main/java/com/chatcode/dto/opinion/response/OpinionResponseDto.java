package com.chatcode.dto.opinion.response;

import com.chatcode.dto.opinion.OpinionVo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpinionResponseDto {
    private long commentId;
    private long postId;
    private long userId;
    private String userName;
    private String avatar;
    private String timestamp;
    private Long parentId;
    private long likeCount;
    private long disLikeCount;
    private long depth;
    private long groupId;

    private long replyCount = 0;
    private Boolean isLiked;
    private boolean isRole;

    public static OpinionResponseDto of(OpinionVo vo, Boolean isLiked, boolean isRole) {
        return OpinionResponseDto.builder().commentId(vo.getId())
                .postId(vo.getPostId())
                .userId(vo.getUserId())
                .userName(vo.getUserName())
                .avatar(vo.getAvatar())
                .timestamp(vo.getTimestamp())
                .parentId(vo.getParentId())
                .depth(vo.getDepth())
                .groupId(vo.getGroupId())
                .likeCount(vo.getLikeCount())
                .disLikeCount(vo.getDisLikeCount())
                .isLiked(isLiked).isRole(isRole).build();
    }
}
