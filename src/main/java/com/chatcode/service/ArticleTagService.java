package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.ArticleTag;
import com.chatcode.domain.entity.Tag;
import com.chatcode.repository.article.ArticleTagRepository;
import com.chatcode.repository.article.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleTagService {
    private final TagRepository tagRepository;
    private final ArticleTagRepository articleTagRepository;

    @Transactional
    public void tagToArticle(Article article, List<String> tags) {
        for (String tagName : tags) {
            Tag tag = findOrCreateTag(tagName);
            tag.incrementCount();
            tagRepository.save(tag);

            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticle(article);
            articleTag.setTag(tag);
            articleTagRepository.save(articleTag);
        }
    }

    @Transactional
    public void updateArticleTags(Article article, List<String> tags) {
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
                tag.incrementCount();
                tagRepository.save(tag);
            }
        }
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
