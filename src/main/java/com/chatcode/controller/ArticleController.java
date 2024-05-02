package com.chatcode.controller;

import com.chatcode.dto.ArticleRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.ArticleResponseDTO.ArticleCreateResponseDTO;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.service.ArticleService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/create")
    public BaseResponseDto<?> createArticle(@Valid @RequestBody ArticleRequestDTO.ArticleCreateRequestDTO params) {
        ArticleCreateResponseDTO responseBody = articleService.articleCreate(params);
        return new BaseResponseDto<>(1, responseBody, "성공적으로 게시글이 등록되었습니다.");
    }

    @PostMapping("/update/{articleId}")
    public BaseResponseDto<?> updateArticle(@PathVariable Long articleId, @RequestBody ArticleUpdateRequestDTO params) {
        articleService.articleUpdate(articleId, params);
        return new BaseResponseDto<>(1, null, "업데이트 성공");
    }

    @GetMapping("/list")
    public BaseResponseDto<List<String>> getArticleList() {
        List<String> articleList = articleService.readArticleList();
        return new BaseResponseDto<>(1, articleList, "게시글 목록 조회 성공");
    }

    @GetMapping("/{articleId}")
    public BaseResponseDto<?> readArticleById(@PathVariable Long articleId) {
        Optional<String> contentText = articleService.readArticleById(articleId);
        if (contentText.isPresent()) {
            return new BaseResponseDto<>(1, contentText.get(), "글 내용 조회 성공");
        } else {
            return new BaseResponseDto<>(0, null, "해당 제목에 대한 글 내용이 없습니다.");
        }
    }

    @DeleteMapping("/delete/{articleId}")
    public BaseResponseDto<?> deleteArticleAndContent(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return new BaseResponseDto<>(1, null, "포스트 삭제 성공");
    }
}
