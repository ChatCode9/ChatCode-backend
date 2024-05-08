package com.chatcode.dto.category;

import com.chatcode.domain.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private Integer sortOrder;

    public static CategoryResponse of(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.id = category.getId();
        response.name = category.getName();
        response.sortOrder = category.getSortOrder();
        return response;
    }
}
