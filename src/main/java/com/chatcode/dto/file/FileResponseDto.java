package com.chatcode.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema
public class FileResponseDto {
    @Schema(description = "Image URL", example = "https://chat-code-bucket.s3.us-east-2.amazonaws.com/article/74513d48-68a9-46a8-a2dd-d349b63b6937.jpg")
    private String url;
}
