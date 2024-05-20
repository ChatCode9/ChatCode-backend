package com.chatcode.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class FileRequestDto {
    @Schema(description = "이미지파일(base64형태)", example = "")
    private String base64File;
    @Schema(description = "이미지가 업로드 될 게시글 번호", example = "1")
    private Long targetId;
}
