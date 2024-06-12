package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.ArticleRepository;
import com.chatcode.repository.article.ArticleWriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleWriteRepository articleWriteRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ArticleWriteRepository articleWriteRepository) {
        this.articleRepository = articleRepository;
        this.articleWriteRepository = articleWriteRepository;
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

    public List<Article> findAll(PageRequest pageRequest) {
        return articleWriteRepository.findAllBy(pageRequest);
    }

    public List<Article> findArticlesOrderByCreateDt(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("dateCreated").descending());

        return articleWriteRepository.findAllBy(pageRequest);
    }
}
