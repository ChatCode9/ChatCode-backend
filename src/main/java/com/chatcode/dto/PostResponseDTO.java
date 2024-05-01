package com.chatcode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PostResponseDTO {

    @ToString
    @Builder
    @Getter
    @Setter
    public static class PostCreateResponseDTO {
        private Long id;
        private String contentText;
        private String title;
        private Long version;
    }
}
