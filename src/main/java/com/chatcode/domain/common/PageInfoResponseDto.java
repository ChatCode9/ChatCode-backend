package com.chatcode.domain.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageInfoResponseDto {
    @Schema(description = "페이지 번호", example = "0", defaultValue = "0")
    private int page;
    @Schema(description = "페이지 사이즈", example = "10", defaultValue = "10")
    private int size;
    private long totalElements;
    private long totalPages;

    public long getTotalPages(){
        return totalElements / size;
    }
}
