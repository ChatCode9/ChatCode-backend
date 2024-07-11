package com.chatcode.controller;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.article.ArticleDetailResponseDto;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.article.ArticleResponseDTO;
import com.chatcode.dto.article.ArticleRetrieveRequest;
import com.chatcode.dto.article.ArticleRetrieveServiceDto;
import com.chatcode.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("")
    @Operation(summary = "Article 작성 (로그인 필요)", description = "Article을 작성합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "성공적으로 게시글이 등록되었습니다.",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 1, \"data\": null, \"message\": \"성공적으로 게시글이 등록되었습니다.\"}"
                    )
            )
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponseDto<Void>> createArticle(@Valid @RequestBody ArticleCreateRequestDTO params,
                                                               @AuthenticationPrincipal LoginUser loginUser,
                                                               BindingResult bindingResult) {
        articleService.articleCreate(params, loginUser.getAvatarId());
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "성공적으로 게시글이 등록되었습니다."));
    }

    @PutMapping("/{articleId}")
    @Operation(summary = "Article 수정 (로그인 필요)", description = "Article을 수정합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "업데이트 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 1, \"data\": null, \"message\": \"업데이트 성공\"}"
                    )
            )
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponseDto<Void>> updateArticle(@PathVariable Long articleId,
                                                               @Valid @RequestBody ArticleUpdateRequestDTO params,
                                                               BindingResult bindingResult) {
        articleService.articleUpdate(articleId, params);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "업데이트 성공"));
    }

    @GetMapping("")
    @Operation(summary = "Article 목록 조회", description = "Article 목록을 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "게시글 목록 조회 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 1, \"data\": [...], \"message\": \"게시글 목록 조회 성공\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<List<ArticleResponseDTO>>> getArticleList(
            @RequestBody ArticleRetrieveRequest requestDto) {
        ArticleRetrieveServiceDto serviceDto = ArticleRetrieveRequest.fromRequestDto(requestDto);
        return ResponseEntity.ok(articleService.findAll(serviceDto));
    }

    @GetMapping("/{articleId}")
    @Operation(summary = "Article 내용 조회", description = "Article 내용을 조회합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "글 내용 조회 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 1, \"data\": \"...\", \"message\": \"글 내용 조회 성공\"}"
                    )
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "해당 제목에 대한 글 내용이 없습니다.",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 0, \"data\": null, \"message\": \"해당 제목에 대한 글 내용이 없습니다.\"}"
                    )
            )
    )
    public ResponseEntity<BaseResponseDto<ArticleDetailResponseDto>> readArticleById(@PathVariable Long articleId, @AuthenticationPrincipal LoginUser loginUser) {
        ArticleDetailResponseDto articleDetailResponseDto = articleService.readArticleById(articleId, loginUser.getAvatarId());
        return ResponseEntity.ok(new BaseResponseDto<>(200, articleDetailResponseDto, "success"));
    }

    @DeleteMapping("/{articleId}")
    @Operation(summary = "Article 삭제 (로그인 필요)", description = "Article을 삭제합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "포스트 삭제 성공",
            content = @Content(
                    examples = @ExampleObject(
                            value = "{\"code\": 1, \"data\": null, \"message\": \"포스트 삭제 성공\"}"
                    )
            )
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponseDto<Void>> deleteArticleAndContent(@PathVariable Long articleId,
                                                                         @AuthenticationPrincipal LoginUser loginUser) {
        if (!articleService.isAuthor(articleId, loginUser.getAvatarId())) {
            return ResponseEntity.ok(new BaseResponseDto<>(0, null, "작성자만 삭제할 수 있습니다."));
        }
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok(new BaseResponseDto<>(1, null, "포스트 삭제 성공"));
    }
}
