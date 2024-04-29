package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.like.LikeRequest;
import com.chatcode.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.chatcode.domain.LikeableContentType.ARTICLE;
import static com.chatcode.domain.LikeableContentType.OPINION;

@RestController
@RequestMapping
public class LikeController {

  @Autowired
  private LikeService likeService;

  @PostMapping("articles/{articleId}/like")
  public ResponseEntity<BaseResponseDto<String>> likeArticle(@PathVariable int articleId, @RequestBody LikeRequest likeRequest) {
    //Todo UserId 실제 UserId로 수정하기
    likeService.like(ARTICLE, articleId, 1, likeRequest);
    return ResponseEntity.ok(new BaseResponseDto<>(200, "", "success"));
  }

  @PostMapping("opinions/{articleId}/like")
  public ResponseEntity<BaseResponseDto<String>> likeOpinion(@PathVariable int articleId, @RequestBody LikeRequest likeRequest) {
    //Todo UserId 실제 UserId로 수정하기
    likeService.like(OPINION, articleId, 1, likeRequest);
    return ResponseEntity.ok(new BaseResponseDto<>(200, "", "success"));
  }
}
