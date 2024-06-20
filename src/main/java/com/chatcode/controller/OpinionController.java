package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.opinion.response.OpinionResponseDto;
import com.chatcode.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("opinions")
@RequiredArgsConstructor
public class OpinionController {
    private final OpinionService opinionService;

    @GetMapping("/{articleId}")
    public ResponseEntity<BaseResponseDto<List<OpinionResponseDto>>> getOpinions(@PathVariable Long articleId,
                                                                                 @AuthenticationPrincipal LoginUser loginUser) {
        List<OpinionResponseDto> results = opinionService.getOpinions(articleId, loginUser.getAvatarId());

        return ResponseEntity.ok(new BaseResponseDto<>(200, results, ""));
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<BaseResponseDto<Void>> createOpinion(@PathVariable Long articleId,
                                                         @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(new BaseResponseDto<>(200, null, ""));
    }
}
