package com.chatcode.dto.article;

import com.chatcode.domain.article.CategoryType;
import com.chatcode.domain.article.SortType;
import com.chatcode.domain.article.StatusType;
import com.chatcode.domain.common.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRetrieveRequest {
    @Schema(description = "검색 키워드", example = "", defaultValue = "")
    private String search = "";
    @Schema(description = "카테고리", example = "free(자유게시판), question(질문게시판)")
    private String category = "";
    @Schema(description = "정렬 조건", example = "latest(최신순), earliest(오래된순)", defaultValue = "latest")
    private String sortBy = "latest";
    @Schema(description = "필터링 조건", example = "wait(해결대기중), finish(해결완료)", defaultValue = "wait,finish")
    private String status = "";
    private int page = 0;
    private int pageSize = 10;

    public static ArticleRetrieveServiceDto fromRequestDto(ArticleRetrieveRequest request) {
        return new ArticleRetrieveServiceDto(
                request.getSearch().isBlank() ? null : request.getSearch(),
                request.getCategory().isBlank() ? null : CategoryType.of(request.getCategory()),
                request.getSortBy().isBlank() ? null : SortType.of(request.getSortBy()),
                request.getStatus().isBlank() ? null : StatusType.of(request.getStatus()),
                new PageInfo(request.getPage(), request.getPageSize())
        );
    }
}
