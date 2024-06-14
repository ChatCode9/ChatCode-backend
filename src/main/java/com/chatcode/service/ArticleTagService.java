package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.ArticleTag;
import com.chatcode.domain.entity.Tag;
import com.chatcode.repository.article.ArticleTagRepository;
import com.chatcode.repository.article.TagRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleTagService {
    private final TagRepository tagRepository;
    private final ArticleTagRepository articleTagRepository;

    private boolean tagsExist(List<String> tags) {
        return tags != null && !tags.isEmpty();
    }

    @Transactional
    public void createTagToArticle(Article article, List<String> tags) {
        if (!tagsExist(tags)) {
            return;
        }
        tags.stream()
                .map(this::findOrCreateTag)
                .forEach(tag -> {
                    tag.incrementCount();
                    tagRepository.save(tag);

                    ArticleTag articleTag = new ArticleTag();
                    articleTag.setArticle(article);
                    articleTag.setTag(tag);
                    articleTagRepository.save(articleTag);
                });
    }

    @Transactional
    public void updateArticleTags(Article article, List<String> tags) {
        if (!tagsExist(tags)) {
            return;
        }
        Set<String> newTagSet = tags.stream().collect(Collectors.toSet());
        List<ArticleTag> existingTags = articleTagRepository.findByArticleId(article.getId());

        existingTags.stream()
                .filter(articleTag -> !newTagSet.contains(articleTag.getTag().getName()))
                .forEach(articleTag -> {
                    articleTagRepository.delete(articleTag);
                    Tag tag = articleTag.getTag();
                    tag.decrementCount();
                    tagRepository.save(tag);
                });

        newTagSet.stream()
                .filter(tagName -> existingTags.stream().noneMatch(articleTag -> articleTag.getTag().getName().equals(tagName)))
                .forEach(tagName -> {
                    Tag tag = findOrCreateTag(tagName);
                    ArticleTag articleTag = new ArticleTag();
                    articleTag.setArticle(article);
                    articleTag.setTag(tag);
                    articleTagRepository.save(articleTag);
                    tag.incrementCount();
                    tagRepository.save(tag);
                });
    }

    @Transactional
    public void removeTagsFromArticle(Long articleId) {
        List<ArticleTag> articleTags = articleTagRepository.findByArticleId(articleId);
        articleTagRepository.deleteAll(articleTags);

        articleTags.forEach(articleTag -> {
            Tag tag = articleTag.getTag();
            tag.decrementCount();
            tagRepository.save(tag);
        });
    }

    private Tag findOrCreateTag(String tagName) {
        return tagRepository.findByName(tagName).orElseGet(() -> {
            Tag newTag = new Tag();
            newTag.setName(tagName);
            newTag.setTagCount(0);
            return tagRepository.save(newTag);
        });
    }
}
