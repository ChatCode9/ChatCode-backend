package com.chatcode.dto.comment.response;

import com.chatcode.dto.comment.CommentVo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentResponseDto {
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

    public static CommentResponseDto of(CommentVo vo, Boolean isLiked, boolean isRole) {
        return CommentResponseDto.builder().commentId(vo.getCommentId())
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
