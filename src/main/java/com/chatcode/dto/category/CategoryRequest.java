package com.chatcode.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

public class CategoryRequest {

    @Data
    @Schema
    public static class CategoryCreateRequest {
        @Schema(description = "카테고리 이름", example = "생성할 카테고리 이름")
        @NotNull
        @Size(min = 1, max = 50)
        private String name;
    }

    @Data
    @Schema
    public static class CategoryUpdateNameRequest {
        @Schema(description = "카테고리 이름", example = "수정할 카테고리 이름")
        @Size(min = 1, max = 50)
        private String name;
    }

    @Data
    @Schema
    public static class CategoryUpdateOrderRequest {
        @Schema(description = "카테고리 순서", example = "[3, 1, 2, 4] (모든 카테고리에 대해서 원하는 순서대로)")
        @NotNull
        private List<Long> orders;
    }

}
