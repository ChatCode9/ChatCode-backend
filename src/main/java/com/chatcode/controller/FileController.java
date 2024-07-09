package com.chatcode.controller;

import com.chatcode.domain.file.ImageFile;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.file.FileRequestDto;
import com.chatcode.dto.file.FileResponseDto;
import com.chatcode.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "파일 업로드 API", description = "파일 업로드 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    @PostMapping
    @Operation(summary = "파일 업로드",
            description = "파일 업로드하는 API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "파일 업로드 성공",
                            content = @Content(schema = @Schema(implementation = BaseResponseDto.class),
                                    examples = @ExampleObject(value = "{\"statusCode\":200,\"data\":{\"url\":\"https://chat-code-bucket.s3.us-east-2.amazonaws.com/article/74513d48-68a9-46a8-a2dd-d349b63b6937.jpg\"},\"message\":\"success\"}"))),
                    @ApiResponse(responseCode = "400", description = "빈 파일 업로드 시도",
                            content = @Content(schema = @Schema(implementation = BaseResponseDto.class),
                                    examples = @ExampleObject(value = "{\"statusCode\":400,\"data\":null,\"message\":\"빈 파일 업로드 시도\"}"))),
                    @ApiResponse(responseCode = "500", description = "파일 업로드 중 에러 발생",
                            content = @Content(schema = @Schema(implementation = BaseResponseDto.class),
                                    examples = @ExampleObject(value = "{\"statusCode\":500,\"data\":null,\"message\":\"파일 업로드 중 에러 발생\"}")))
            })
    public ResponseEntity<BaseResponseDto<FileResponseDto>> uploadFile(@RequestBody FileRequestDto dto) {
        ImageFile imageFile = new ImageFile(dto.getBase64File());
        String imgPath = fileService.uploadImages(imageFile, dto.getTargetId());

        return ResponseEntity.ok(new BaseResponseDto<>(200, new FileResponseDto(imgPath), "success"));
    }
}
