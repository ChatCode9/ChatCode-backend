package com.chatcode.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ArticleVo {
    private Long id;
    private LocalDateTime dateCreated;
    private String picture;
    private String nickname;
    private String title;
    private String text;
    private String tagString;
    private Boolean enabled;
    private Integer viewCount;
    //todo 대댓글 기능 개발 이후 수정하기
    private Integer commentCount = 0;
    private Integer likeCount;
}
