package com.chatcode.controller;

import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.PostRequestDTO.PostCreateRequestDTO;
import com.chatcode.dto.PostRequestDTO.PostUpdateRequestDTO;
import com.chatcode.dto.PostResponseDTO.PostCreateResponseDTO;
import com.chatcode.service.PostService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public BaseResponseDto<?> createPost(@Valid @RequestBody PostCreateRequestDTO params) {
        PostCreateResponseDTO responseBody = postService.postCreate(params);
        return new BaseResponseDto<>(1, responseBody, "성공적으로 게시글이 등록되었습니다.");
    }

    @PostMapping("/update/{articleId}")
    public BaseResponseDto<?> updatePost(@PathVariable Long articleId, @RequestBody PostUpdateRequestDTO params) {
        postService.postUpdate(articleId, params);
        return new BaseResponseDto<>(1, null, "업데이트 성공");
    }


    @GetMapping("/list")
    public BaseResponseDto<List<String>> getPostList() {
        List<String> postList = postService.readPostList();
        return new BaseResponseDto<>(1, postList, "게시글 목록 조회 성공");
    }

    @GetMapping("/{articleId}")
    public BaseResponseDto<?> readPostById(@PathVariable Long articleId) {
        Optional<String> contentText = postService.readPostById(articleId);
        if (contentText.isPresent()) {
            return new BaseResponseDto<>(1, contentText.get(), "글 내용 조회 성공");
        } else {
            return new BaseResponseDto<>(0, null, "해당 제목에 대한 글 내용이 없습니다.");
        }
    }

    @DeleteMapping("/delete/{articleId}")
    public BaseResponseDto<?> deleteArticleAndContent(@PathVariable Long articleId) {
        postService.deletePost(articleId);
        return new BaseResponseDto<>(1, null, "포스트 삭제 성공");
    }
}
