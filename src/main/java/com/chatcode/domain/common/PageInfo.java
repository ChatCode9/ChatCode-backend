package com.chatcode.domain.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    @Schema(description = "페이지 번호", example = "0", defaultValue = "0")
    private int page;
    @Schema(description = "페이지 사이즈", example = "10", defaultValue = "10")
    private int size;

    public long getOffset() {
        return (long) page * size;
    }

    public static PageInfoResponseDto of(PageInfo pageInfo, long totalElements) {
        return PageInfoResponseDto.builder()
                .page(pageInfo.getPage())
                .size(pageInfo.getSize())
                .totalElements(totalElements).build();
    }
}
