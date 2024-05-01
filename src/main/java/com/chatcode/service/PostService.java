package com.chatcode.service;

import com.chatcode.dto.PostRequestDTO.PostCreateRequestDTO;
import com.chatcode.dto.PostRequestDTO.PostUpdateRequestDTO;
import com.chatcode.dto.PostResponseDTO.PostCreateResponseDTO;
import com.chatcode.repository.PostRepository;
import com.chatcode.repository.PostRepository.ContentNotFoundException;
import org.jooq.Record2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    private PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public static PostService createPostService(PostRepository postRepository) {
        return new PostService(postRepository);
    }

    public PostCreateResponseDTO postCreate(PostCreateRequestDTO params) {
        return postRepository.createPost(params);
    }



    public void postUpdate(Long articleId, PostUpdateRequestDTO updateDTO) {
        // 아티클 아이디에 매핑된 콘텐츠 아이디 조회
        Long contentId = postRepository.findContentIdByArticleId(articleId);
        if (contentId != null) {
            postRepository.updatePost(articleId, contentId, updateDTO);
        } else {
            throw new ContentNotFoundException("해당 아티클에 대한 콘텐츠가 없습니다.");
        }
    }


    public List<String> readPostList() {
        return postRepository.readPostList();
    }

    public Optional<String>  readPostById(Long articleId) {
        return postRepository.readPostById(articleId);
    }


    public void deletePost(Long articleId) {
        postRepository.deletePost(articleId);

    }


}
