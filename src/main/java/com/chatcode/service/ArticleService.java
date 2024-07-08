package com.chatcode.service;

import com.chatcode.domain.LikeableContentType;
import com.chatcode.domain.article.ArticleVo;
import com.chatcode.domain.common.PageInfo;
import com.chatcode.domain.entity.Article;
import com.chatcode.dto.BaseResponseDto;
import com.chatcode.dto.article.ArticleDetailResponseDto;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.article.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.dto.article.ArticleResponseDTO;
import com.chatcode.dto.article.ArticleRetrieveServiceDto;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.ArticleRepository;
import com.chatcode.repository.RedisReactionRepository;
import com.chatcode.repository.article.ArticleReadRepository;
import com.chatcode.repository.article.ArticleWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleWriteRepository articleWriteRepository;
    private final ArticleReadRepository articleReadRepository;
    private final RedisReactionRepository redisReactionRepository;
    private final ArticleTagService articleTagService;

    public void articleCreate(ArticleCreateRequestDTO params) {
        Long articleId = articleRepository.createArticle(params);

        Article article = articleWriteRepository.findById(articleId)
                .orElseThrow(() -> new ContentNotFoundException("Article not found"));

        articleTagService.createTagToArticle(article, params.getTags());
    }

    @Transactional
    public void articleUpdate(Long articleId, ArticleUpdateRequestDTO updateDTO) {
        Long contentId = Optional.ofNullable(articleRepository.findContentIdByArticleId(articleId))
                .orElseThrow(() -> new ContentNotFoundException("해당 아티클에 대한 콘텐츠가 없습니다."));

        articleRepository.updateArticle(articleId, contentId, updateDTO);

        Article article = articleWriteRepository.findById(articleId)
                .orElseThrow(() -> new ContentNotFoundException("Article not found"));

        articleTagService.updateArticleTags(article, updateDTO.getTags());
    }

    public ArticleDetailResponseDto readArticleById(Long articleId) {
        ArticleVo articleById = articleReadRepository.findArticleById(articleId);

        //todo 실제 userId로
        Boolean isLiked = redisReactionRepository.checkAlreadyLiked(LikeableContentType.OPINION, articleId, 1).orElse(false);

        return ArticleDetailResponseDto.of(articleById, isLiked);
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        articleTagService.removeTagsFromArticle(articleId);
        articleRepository.deleteArticle(articleId);
    }

    public BaseResponseDto<List<ArticleResponseDTO>> findAll(ArticleRetrieveServiceDto serviceDto) {
        List<ArticleResponseDTO> list = articleReadRepository.retrieve(serviceDto).stream().map(ArticleResponseDTO::of).toList();
        Long totalElements = articleReadRepository.getTotalElements(serviceDto);

        return new BaseResponseDto<>(200, list, "", PageInfo.of(serviceDto.getPageInfo(), totalElements));
    }
}
