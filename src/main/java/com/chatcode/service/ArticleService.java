package com.chatcode.service;

import com.chatcode.dto.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public void articleCreate(ArticleCreateRequestDTO params) {
        articleRepository.createArticle(params);
    }

    public void articleUpdate(Long articleId, ArticleUpdateRequestDTO updateDTO) {
        Long contentId = articleRepository.findContentIdByArticleId(articleId);
        if (contentId == null) {
            throw new ContentNotFoundException("해당 아티클에 대한 콘텐츠가 없습니다.");
        }
        articleRepository.updateArticle(articleId, contentId, updateDTO);
    }

    public List<String> readArticleList() {
        return articleRepository.readArticleList();
    }

    public Optional<String> readArticleById(Long articleId) {
        return articleRepository.readArticleById(articleId);
    }


    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepository.deleteArticle(articleId);
    }

}
