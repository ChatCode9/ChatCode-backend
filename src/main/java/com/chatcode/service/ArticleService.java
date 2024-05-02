package com.chatcode.service;

import com.chatcode.dto.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.ArticleResponseDTO.ArticleCreateResponseDTO;
import com.chatcode.repository.ArticleRepository;
import com.chatcode.repository.ArticleRepository.ContentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    private ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleCreateResponseDTO articleCreate(ArticleCreateRequestDTO params) {
        return articleRepository.createArticle(params);
    }

    public void articleUpdate(Long articleId, ArticleUpdateRequestDTO updateDTO) {
        // 아티클 아이디에 매핑된 콘텐츠 아이디 조회
        Long contentId = articleRepository.findContentIdByArticleId(articleId);
        if (contentId != null) {
            articleRepository.updateArticle(articleId, contentId, updateDTO);
        } else {
            throw new ContentNotFoundException("해당 아티클에 대한 콘텐츠가 없습니다.");
        }
    }

    public List<String> readArticleList() {
        return articleRepository.readArticleList();
    }

    public Optional<String> readArticleById(Long articleId) {
        return articleRepository.readArticleById(articleId);
    }


    public void deleteArticle(Long articleId) {
        articleRepository.deleteArticle(articleId);

    }


}
