package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.article.ArticleResponseDTO;
import com.chatcode.dto.article.ArticleRetrieveRequest;
import com.chatcode.dto.article.ArticleRetrieveServiceDto;
import com.chatcode.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("")
    @Operation(summary = "Article 작성", description = "Article을 작성합니다.")
    public ResponseEntity<BaseResponseDto<Void>> createArticle(@Valid @RequestBody ArticleCreateRequestDTO params,
                                                               BindingResult bindingResult) {
        articleService.articleCreate(params);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "성공적으로 게시글이 등록되었습니다."));
    }

    @PutMapping("/{articleId}")
    @Operation(summary = "Article 수정", description = "Article을 수정합니다.")
    public ResponseEntity<BaseResponseDto<Void>> updateArticle(@PathVariable Long articleId,
                                                               @Valid @RequestBody ArticleUpdateRequestDTO params,
                                                               BindingResult bindingResult) {
        articleService.articleUpdate(articleId, params);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "업데이트 성공"));
    }

    @GetMapping("")
    @Operation(summary = "Article 목록 조회", description = "Article 목록을 조회합니다.")
    public ResponseEntity<BaseResponseDto<List<ArticleResponseDTO>>> getArticleList(
            @RequestBody ArticleRetrieveRequest requestDto) {
        ArticleRetrieveServiceDto serviceDto = ArticleRetrieveRequest.fromRequestDto(requestDto);
        return ResponseEntity.ok(articleService.findAll(serviceDto));
    }

    @GetMapping("/{articleId}")
    @Operation(summary = "Article 내용 조회", description = "Article 내용을 조회합니다.")
    public ResponseEntity<BaseResponseDto<Object>> readArticleById(@PathVariable Long articleId) {
        Optional<String> contentText = articleService.readArticleById(articleId);
        if (contentText.isPresent()) {
            return ResponseEntity.ok(new BaseResponseDto<>(1, contentText.get(), "글 내용 조회 성공"));
        }
        return ResponseEntity.ok(new BaseResponseDto<>(0, null, "해당 제목에 대한 글 내용이 없습니다."));
    }

    @DeleteMapping("/{articleId}")
    @Operation(summary = "Article 삭제", description = "Article을 삭제합니다.")
    public ResponseEntity<BaseResponseDto<Void>> deleteArticleAndContent(@PathVariable Long articleId) {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "포스트 삭제 성공"));
    }
}
