package com.chatcode.dto.article;

import com.chatcode.domain.article.CategoryType;
import com.chatcode.domain.article.SortType;
import com.chatcode.domain.article.StatusType;
import com.chatcode.domain.common.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ArticleRetrieveServiceDto {
    @Schema(description = "검색 키워드", example = "", defaultValue = "")
    private final String search;
    @Schema(description = "카테고리", example = "free(자유게시판), question(질문게시판)")
    private final CategoryType category;
    @Schema(description = "정렬 조건", example = "latest(최신순), earliest(오래된순)", defaultValue = "latest")
    private final SortType sortBy;
    @Schema(description = "필터링 조건", example = "wait(해결대기중), finish(해결완료)", defaultValue = "wait,finish")
    private final StatusType status;
    private final PageInfo pageInfo;
}