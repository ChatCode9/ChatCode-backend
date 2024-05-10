package com.chatcode.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema
public class FileRequestDto {
    @Schema(description = "이미지파일(base64형태)", example = "")
    private MultipartFile base64File;
    @Schema(description = "이미지가 업로드 될 게시글 번호", example = "1")
    private Long targetId;
}
