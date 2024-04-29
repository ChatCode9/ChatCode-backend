package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.User;
import com.chatcode.service.JooqArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

  @Autowired
  private JooqArticleService service;

  @PostMapping("/{id}/like")
  public ResponseEntity<BaseResponseDto<String>> like(@PathVariable("id") Integer id) {
    service.like(id, new User());
    return ResponseEntity.ok(new BaseResponseDto<>(200, "", "success"));
  }

  @PostMapping("/{id}/dislike")
  public ResponseEntity<BaseResponseDto<String>> dislike(@PathVariable("id") Integer id) {
    service.dislike(id, new User());
    return ResponseEntity.ok(new BaseResponseDto<>(200, "", "success"));
  }
}
