package com.chatcode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRetrieveRequest{
    private int page = 0;
    private int size = 10;
    private String order = "DESC";
    private String sort = "dateCreated";

    public static PageRequest from(ArticleRetrieveRequest pageRequest) {
        Sort.Direction direction = "ASC".equalsIgnoreCase(pageRequest.getOrder()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        return PageRequest.of(pageRequest.getPage(), pageRequest.getSize(), Sort.by(direction, pageRequest.getSort()));
    }
}
