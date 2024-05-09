package com.chatcode.dto.category;

import com.chatcode.domain.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema
@NoArgsConstructor
@Data
public class CategoryResponse {
    @Schema(description = "카테고리 ID", example = "DB 인덱스 번호")
    private Long id;
    @Schema(description = "카테고리 이름", example = "카테고리 이름")
    private String name;
    @Schema(description = "카테고리 정렬 순서", example = "카테고리 정렬 순서")
    private Integer sortOrder;

    public static CategoryResponse of(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.id = category.getId();
        response.name = category.getName();
        response.sortOrder = category.getSortOrder();
        return response;
    }
}
