package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.ArticleTag;
import com.chatcode.domain.entity.Tag;
import com.chatcode.dto.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.article.TagRepository;
import com.chatcode.repository.article.ArticleRepository;
import com.chatcode.repository.article.ArticleTagRepository;
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
    private final TagRepository tagRepository;
    private final ArticleTagRepository articleTagRepository;
    private final ArticleWriteRepository articleWriteRepository;

    @Transactional
    public void articleCreate(ArticleCreateRequestDTO params) {
        Long articleId = articleRepository.createArticle(params);

        if (params.getTags() != null && !params.getTags().isEmpty()) {
            Article article = articleWriteRepository.findById(articleId)
                    .orElseThrow(() -> new RuntimeException("Article not found"));
            tagToArticle(article, params.getTags());
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
                .orElseThrow(() -> new RuntimeException("Article not found"));

        updateArticleTags(article, updateDTO.getTags());
    }

    public List<String> readArticleList() {
        return articleRepository.readArticleList();
    }

    public Optional<String> readArticleById(Long articleId) {
        return articleRepository.readArticleById(articleId);
    }


    @Transactional
    public void deleteArticle(Long articleId) {
        List<ArticleTag> articleTags = articleTagRepository.findByArticleId(articleId);
        articleTagRepository.deleteAll(articleTags);

        articleTags.forEach(articleTag -> {
            Tag tag = articleTag.getTag();
            tag.setTagCount(tag.getTagCount() - 1);
            tagRepository.save(tag);
        });

        articleRepository.deleteArticle(articleId);
    }

    private void tagToArticle(Article article, List<String> tag) {
        for (String tagName : tag) {
            Tag tags = findOrCreateTag(tagName);
            tagCount(tags);

            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticle(article);
            articleTag.setTag(tags);
            articleTagRepository.save(articleTag);
        }
    }

    private void updateArticleTags(Article article, List<String> tags) {
        List<ArticleTag> existingTags = articleTagRepository.findByArticleId(article.getId());

        for (ArticleTag articleTag : existingTags) {
            if (!tags.contains(articleTag.getTag().getName())) {
                articleTagRepository.delete(articleTag);
                Tag tag = articleTag.getTag();
                tag.setTagCount(tag.getTagCount() - 1);
                tagRepository.save(tag);
            }
        }

        for (String tagName : tags) {
            Tag tag = findOrCreateTag(tagName);
            if (existingTags.stream().noneMatch(at -> at.getTag().getName().equals(tagName))) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setArticle(article);
                articleTag.setTag(tag);
                articleTagRepository.save(articleTag);
                tagCount(tag);
            }
        }

    }

    private Tag findOrCreateTag(String tagName) {
        return tagRepository.findByName(tagName).orElseGet(() -> {
            Tag newTag = new Tag();
            newTag.setName(tagName);
            newTag.setTagCount(0);
            return tagRepository.save(newTag);
        });
    }
    private void tagCount(Tag tag) {
        tag.setTagCount(tag.getTagCount() + 1);
        tagRepository.save(tag);
    }
}
