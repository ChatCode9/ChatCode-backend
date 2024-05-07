package com.chatcode.controller;

import com.chatcode.dto.ArticleRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.service.ArticleService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @PostMapping("")
    public ResponseEntity<BaseResponseDto<Void>> createArticle(@Valid @RequestBody ArticleRequestDTO.ArticleCreateRequestDTO params) {
        articleService.articleCreate(params);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "성공적으로 게시글이 등록되었습니다."));
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<BaseResponseDto<Void>> updateArticle(@PathVariable Long articleId, @RequestBody ArticleUpdateRequestDTO params) {
        articleService.articleUpdate(articleId, params);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "업데이트 성공"));
    }

    @GetMapping("")
    public ResponseEntity<BaseResponseDto<List<String>>> getArticleList() {
        List<String> articleList = articleService.readArticleList();
        return ResponseEntity.ok(new BaseResponseDto<>(1, articleList, "게시글 목록 조회 성공"));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<BaseResponseDto<Object>> readArticleById(@PathVariable Long articleId) {
        Optional<String> contentText = articleService.readArticleById(articleId);
        if (contentText.isPresent()) {
            return ResponseEntity.ok(new BaseResponseDto<>(1, contentText.get(), "글 내용 조회 성공"));
        }
        return ResponseEntity.ok(new BaseResponseDto<>(0, null, "해당 제목에 대한 글 내용이 없습니다."));
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<BaseResponseDto<Void>> deleteArticleAndContent(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "포스트 삭제 성공"));
    }
}
