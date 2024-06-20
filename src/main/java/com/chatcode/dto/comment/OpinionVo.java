package com.chatcode.dto.comment;

import lombok.Data;

@Data
public class OpinionVo {
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

}
