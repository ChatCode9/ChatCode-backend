package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.comment.response.CommentResponseDto;
import com.chatcode.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public ResponseEntity<BaseResponseDto<List<CommentResponseDto>>> getComments(@PathVariable Long articleId,
                                                                                 @AuthenticationPrincipal LoginUser loginUser) {
        List<CommentResponseDto> results = commentService.getComments(articleId, loginUser.getAvatarId());

        return ResponseEntity.ok(new BaseResponseDto<>(200, results, ""));
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<BaseResponseDto<Void>> comment(@PathVariable Long articleId,
                                                         @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(new BaseResponseDto<>(200, null, ""));
    }
}
