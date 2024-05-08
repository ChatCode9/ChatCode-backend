package com.chatcode.dto.category;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Data;

public class CategoryRequest {

    @Data
    public static class CategoryCreateRequest {
        @NotNull
        @Size(min = 1, max = 50)
        private String name;
    }

    @Data
    public static class CategoryUpdateNameRequest {
        @Size(min = 1, max = 50)
        private String name;
    }

    @Data
    public static class CategoryUpdateOrderRequest {
        @NotNull
        private List<Long> orders;
    }

}
