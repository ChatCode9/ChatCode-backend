package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
//import com.chatcode.dto.avatar.AvatarResponse;
import com.chatcode.dto.scrap.ScrapRequestDTO;
import com.chatcode.dto.scrap.ScrapResponseDTO;
import com.chatcode.service.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Scrap API", description = "게시물 스크랩 API")
@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @PostMapping("articles/{articleId}/scrap")
    @Operation(summary = "스크랩 추가", description = "게시물을 스크랩합니다.")
    public ResponseEntity<BaseResponseDto<ScrapResponseDTO>> scrap(@RequestBody ScrapRequestDTO requestDTO) {
        ScrapResponseDTO scrapResponseDTO = scrapService.scrap(requestDTO);
        BaseResponseDto<ScrapResponseDTO> responseDto = new BaseResponseDto<>(200, scrapResponseDTO, "스크랩 추가 성공");
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{avatarId}")
    @Operation(summary = "스크랩 목록 조회", description = "스크랩 했던 게시물 목록을 조회할 수 있습니다.")
    public ResponseEntity<BaseResponseDto<List<ScrapResponseDTO>>> getScrapList(@PathVariable Long avatarId) {
        List<ScrapResponseDTO> scrapList = scrapService.getScrapList(avatarId);
        BaseResponseDto<List<ScrapResponseDTO>> responseDto = new BaseResponseDto<>(200, scrapList, "스크랩 목록 조회 성공");
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{avatarId}/{articleId}")
    @Operation(summary = "스크랩 삭제", description = "스크랩했던 게시물을 목록에서 지울 수 있습니다.")
    public ResponseEntity<BaseResponseDto<Void>> deleteScrap(@PathVariable Long avatarId, @PathVariable Long articleId) {
        scrapService.deleteScrap(avatarId, articleId);
        BaseResponseDto<Void> responseDto = new BaseResponseDto<>(200, null, "스크랩 삭제 성공");
        return ResponseEntity.ok(responseDto);
    }
}