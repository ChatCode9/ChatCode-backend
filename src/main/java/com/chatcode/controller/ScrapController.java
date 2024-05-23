package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.ScrapResponseDto;
import com.chatcode.service.ScrapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Scrap API", description = "게시물 스크랩 API")
@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("articles/{articleId}/scraps")
    @Operation(summary = "스크랩 추가", description = "게시물을 스크랩합니다.")
    public ResponseEntity<BaseResponseDto<ScrapResponseDto>> scrap(@PathVariable @Valid Long articleId,
                                                                   @AuthenticationPrincipal LoginUser loginUser) {
        ScrapResponseDto scrapResponseDto = scrapService.scrap(articleId, loginUser.getAvatarId());
        BaseResponseDto<ScrapResponseDto> responseDto = new BaseResponseDto<>(200, scrapResponseDto, "스크랩 추가 성공");
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("avatars/{avatarId}/scraps")
    @Operation(summary = "스크랩 목록 조회", description = "스크랩 했던 게시물 목록을 조회할 수 있습니다.")
    public ResponseEntity<BaseResponseDto<List<ScrapResponseDto>>> getScrapList(@PathVariable("avatarId") Long avatarId) {
        List<ScrapResponseDto> scrapList = scrapService.getScrapList(avatarId);
        BaseResponseDto<List<ScrapResponseDto>> responseDto = new BaseResponseDto<>(200, scrapList, "스크랩 목록 조회 성공");
        return ResponseEntity.ok(responseDto);
    }

    @PreAuthorize("isAuthenticated() and #loginUser.avatarId == #avatarId")
    @DeleteMapping("avatars/{avatarId}/{articleId}")
    @Operation(summary = "스크랩 삭제", description = "스크랩했던 게시물을 목록에서 지울 수 있습니다.")
    public ResponseEntity<BaseResponseDto<Void>> deleteScrap(@PathVariable @Valid Long articleId,
                                                             @AuthenticationPrincipal LoginUser loginUser,
                                                             @PathVariable("avatarId") Long avatarId) {
        scrapService.deleteScrap(articleId);
        BaseResponseDto<Void> responseDto = new BaseResponseDto<>(200, null, "스크랩 삭제 성공");
        return ResponseEntity.ok(responseDto);
    }
}