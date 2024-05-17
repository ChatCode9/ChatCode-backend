package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.dto.ArticleRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticlePageRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.ArticleResponseDTO;
import com.chatcode.dto.ArticleResponseDTO.ArticlePageResponseDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.ArticleListRepository;
import com.chatcode.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleListRepository articleListRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleListRepository articleListRepository) {
        this.articleRepository = articleRepository;
        this.articleListRepository = articleListRepository;
    }

    public void articleCreate(ArticleCreateRequestDTO params) {
        articleRepository.createArticle(params);
    }

    public void articleUpdate(Long articleId, ArticleUpdateRequestDTO updateDTO) {
        // 아티클 아이디에 매핑된 콘텐츠 아이디 조회
        Long contentId = articleRepository.findContentIdByArticleId(articleId);
        if (contentId == null) {
            throw new ContentNotFoundException("해당 아티클에 대한 콘텐츠가 없습니다.");
        }
        articleRepository.updateArticle(articleId, contentId, updateDTO);
    }

    public Page<ArticlePageResponseDTO> readArticleList(Pageable pageable) {
        return articleListRepository.findAllByOrderByidDesc(pageable)
                .map(article -> new ArticlePageResponseDTO(article.getId(), article.getTitle()));
    }

    public Optional<String> readArticleById(Long articleId) {
        return articleRepository.readArticleById(articleId);
    }


    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepository.deleteArticle(articleId);
    }


}
