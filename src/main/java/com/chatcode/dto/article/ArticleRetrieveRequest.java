package com.chatcode.dto.article;

import com.chatcode.domain.article.CategoryType;
import com.chatcode.domain.article.SortType;
import com.chatcode.domain.article.StatusType;
import com.chatcode.domain.common.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRetrieveRequest {
    private String search = "";
    private CategoryType category;
    private SortType sortBy = SortType.LATEST;
    private List<String> status = Arrays.asList(StatusType.WAIT, StatusType.FINISH);
    private PageInfo pageInfo = new PageInfo(0, 10);

    public static PageRequest from(ArticleRetrieveRequest pageRequest) {
        Sort.Direction direction = pageRequest.getSortBy().getDirection();
        return PageRequest.of(pageRequest.getPageInfo().getPage(), pageRequest.getPageInfo().getSize(), Sort.by(direction, pageRequest.getSortBy().getField()));
    }
}
