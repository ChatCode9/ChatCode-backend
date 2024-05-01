package com.chatcode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class PostRequestDTO {

    @ToString
    @Getter
    @Setter
    @Builder
    public static class PostCreateRequestDTO {
        //content
        @NotNull
        @NotEmpty
        @Size(max = 10_000)
        private String contentText;

        //article
        @Size(max = 10)
        @NotNull
        @NotEmpty
        private String title;
    }

    @ToString
    @Getter
    @Setter
    public static class PostUpdateRequestDTO {
        //content
        @NotNull
        @NotEmpty
        @Size(max = 10_000)
        private String contentText;
        private int contentId;

        //article
        @Size(max = 100)
        @NotNull
        @NotEmpty
        private String title;

        @NotNull
        private Long postId;
    }



}
