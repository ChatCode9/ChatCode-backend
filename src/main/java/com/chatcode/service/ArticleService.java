package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.article.ArticleResponseDTO;
import com.chatcode.dto.article.ArticleRetrieveServiceDto;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.ArticleRepository;
import com.chatcode.repository.article.ArticleReadRepository;
import com.chatcode.repository.article.ArticleWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleWriteRepository articleWriteRepository;
    private final ArticleReadRepository articleReadRepository;

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

    public Optional<String> readArticleById(Long articleId) {
        return articleRepository.readArticleById(articleId);
    }


    @Transactional
    public void deleteArticle(Long articleId) {
        articleRepository.deleteArticle(articleId);
    }

    public List<ArticleResponseDTO> findAll(ArticleRetrieveServiceDto serviceDto) {
        return articleReadRepository.retrieve(serviceDto);
    }
}
