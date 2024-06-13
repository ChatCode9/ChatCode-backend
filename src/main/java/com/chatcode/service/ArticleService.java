package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.dto.article.ArticleRequestDto.ArticleCreateRequestDTO;
import com.chatcode.dto.article.ArticleRequestDto.ArticleUpdateRequestDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.article.ArticleRepository;
import com.chatcode.repository.article.ArticleWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleWriteRepository articleWriteRepository;
    private final ArticleTagService articleTagService;

    @Transactional
    public void articleCreate(ArticleCreateRequestDTO params) {
        Long articleId = articleRepository.createArticle(params);

        if (params.getTags() != null && !params.getTags().isEmpty()) {
            Article article = articleWriteRepository.findById(articleId)
                    .orElseThrow(() -> new ContentNotFoundException("Article not found"));
            articleTagService.tagToArticle(article, params.getTags());
        }
    }

    @Transactional
    public void articleUpdate(Long articleId, ArticleUpdateRequestDTO updateDTO) {
        Long contentId = articleRepository.findContentIdByArticleId(articleId);
        if (contentId == null) {
            throw new ContentNotFoundException("해당 아티클에 대한 콘텐츠가 없습니다.");
        }
        articleRepository.updateArticle(articleId, contentId, updateDTO);

        Article article = articleWriteRepository.findById(articleId)
                .orElseThrow(() -> new ContentNotFoundException("Article not found"));

        articleTagService.updateArticleTags(article, updateDTO.getTags());
    }

    public List<String> readArticleList() {
        return articleRepository.readArticleList();
    }

    public Optional<String> readArticleById(Long articleId) {
        return articleRepository.readArticleById(articleId);
    }


    @Transactional
    public void deleteArticle(Long articleId) {
        articleTagService.removeTagsFromArticle(articleId);
        articleRepository.deleteArticle(articleId);
    }
}
