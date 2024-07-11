package com.chatcode.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryRequest {

    @Data
    @Schema
    public static class CategoryCreateRequest {
        @Schema(description = "카테고리 이름", example = "생성할 카테고리 이름")
        @NotNull
        @Size(min = 1, max = 50, message = "카테고리 이름은 1자 이상 50자 이하로 입력해주세요.")
        private String name;
    }

    @Data
    @Schema
    public static class CategoryUpdateNameRequest {
        @Schema(description = "카테고리 이름", example = "수정할 카테고리 이름")
        @Size(min = 1, max = 50, message = "카테고리 이름은 1자 이상 50자 이하로 입력해주세요.")
        private String name;
    }

    @Data
    @Schema
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryUpdateOrderRequest {
        @Schema(description = "카테고리 순서", example = "[3, 1, 2, 4] (모든 카테고리에 대해서 원하는 순서대로)")
        @NotNull
        private List<Long> orders;

        @AssertTrue(message = "중복된 카테고리 아이디가 존재합니다.")
        public boolean isDuplicated() {
            return orders.size() == orders.stream().distinct().count();
        }
    }

}
