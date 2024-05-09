package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.like.LikeRequest;
import com.chatcode.service.LikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.chatcode.domain.LikeableContentType.ARTICLE;
import static com.chatcode.domain.LikeableContentType.OPINION;

@Tag(name = "좋아요 API", description = "게시글 및 댓글에 대한 좋아요와 싫어요에 관한 API")
@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class LikeController {

  private final LikeService likeService;

  @PostMapping("articles/{articleId}/like")
  @Operation(summary = "게시글 좋아요 및 싫어요",
          description = "게시글에 좋아요 혹은 싫어요를 할 수 있다.(USER 권한 필요)")
  @ApiResponse(responseCode = "200", description = "게시글 좋아요 성공")
  @ApiResponse(responseCode = "400", description = "잘못된 게시글 ID에 대한 요청")
  @ApiResponse(responseCode = "400", description = "이미 해당 게시글에 대하여 좋아요를 한 경우")
  public ResponseEntity<BaseResponseDto<String>> likeArticle(@PathVariable int articleId, @RequestBody LikeRequest likeRequest) {
    //Todo UserId 실제 UserId로 수정하기
    likeService.like(ARTICLE, articleId, 1, likeRequest);
    return ResponseEntity.ok(new BaseResponseDto<>(200, "", "success"));
  }

  @PostMapping("opinions/{opinionId}/like")
  @Operation(summary = "댓글 좋아요 및 싫어요",
          description = "댓글에 좋아요 혹은 싫어요를 할 수 있다.(USER 권한 필요)")
  @ApiResponse(responseCode = "200", description = "댓글 좋아요 성공")
  @ApiResponse(responseCode = "400", description = "잘못된 댓글 ID에 대한 요청")
  @ApiResponse(responseCode = "400", description = "이미 해당 댓글 대하여 좋아요를 한 경우")
  public ResponseEntity<BaseResponseDto<String>> likeOpinion(@PathVariable int opinionId, @RequestBody LikeRequest likeRequest) {
    //Todo UserId 실제 UserId로 수정하기
    likeService.like(OPINION, opinionId, 1, likeRequest);
    return ResponseEntity.ok(new BaseResponseDto<>(200, "", "success"));
  }
}
