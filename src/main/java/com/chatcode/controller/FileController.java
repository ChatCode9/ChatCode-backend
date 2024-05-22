package com.chatcode.controller;

import com.chatcode.domain.file.ImageFile;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.file.FileRequestDto;
import com.chatcode.dto.file.FileResponseDto;
import com.chatcode.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "파일 업로드 API", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @PostMapping
    @Operation(summary = "파일 업로드",
            description = "파일 업로드하는 API")
    @ApiResponse(responseCode = "200", description = "파일 업로드 성공")
    @ApiResponse(responseCode = "400", description = "빈 파일 업로드 시도")
    @ApiResponse(responseCode = "500", description = "파일 업로드 중 에러 발생")
    public ResponseEntity<BaseResponseDto<FileResponseDto>> uploadFile(@RequestBody FileRequestDto dto) {
        ImageFile imageFile = new ImageFile(dto.getBase64File());
        String imgPath = fileService.uploadImages(imageFile, dto.getTargetId());

        return ResponseEntity.ok(new BaseResponseDto<>(200, new FileResponseDto(imgPath), "success"));
    }
}
