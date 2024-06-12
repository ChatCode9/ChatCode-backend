package com.chatcode.repository.article;

import static com.chatcode.jooq.Tables.ARTICLE;
import static com.chatcode.jooq.Tables.CONTENT;
import static org.jooq.impl.DSL.currentLocalDateTime;
import com.chatcode.dto.ArticleRequestDTO.ArticleCreateRequestDTO;
import com.chatcode.dto.ArticleRequestDTO.ArticleUpdateRequestDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.jooq.tables.Article;
import com.chatcode.jooq.tables.Content;
import com.chatcode.jooq.tables.records.ArticleRecord;
import com.chatcode.jooq.tables.records.ContentRecord;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {
    private final DSLContext dslContext;

    public Long createArticle(ArticleCreateRequestDTO articleDTO) {

        ContentRecord contentRecord;
        contentRecord = dslContext
                .insertInto(CONTENT)
                .set(CONTENT.TEXT, articleDTO.getContentText())
                .set(CONTENT.TYPE, 1)
                .set(CONTENT.VERSION, 1L)
                .set(CONTENT.LIKE_COUNT, 0)
                .set(CONTENT.DISLIKE_COUNT, 0)
                .set(CONTENT.DATE_CREATED, currentLocalDateTime())
                .set(CONTENT.LAST_UPDATED, currentLocalDateTime())
                .returning()
                .fetchOne();

        ArticleRecord articleRecord;
        articleRecord = (ArticleRecord) dslContext
                .insertInto(ARTICLE)
                .set(ARTICLE.CONTENT_ID, contentRecord.getId())
                .set(ARTICLE.TITLE, articleDTO.getTitle())
                .set(ARTICLE.NOTE_COUNT, 0)
                .set(ARTICLE.SCRAP_COUNT, 0)
                .set(ARTICLE.VIEW_COUNT, 0)
                .set(ARTICLE.CATEGORY_ID, UUID.randomUUID().toString())
                .set(ARTICLE.VERSION, 1L)
                .set(ARTICLE.LIKE_COUNT, 0)
                .set(ARTICLE.DISLIKE_COUNT, 0)
                .set(ARTICLE.DATE_CREATED, currentLocalDateTime())
                .set(ARTICLE.LAST_UPDATED, currentLocalDateTime())
                .set(ARTICLE.ENABLED, true)
                .set(ARTICLE.TAG_STRING, String.join(" ", articleDTO.getTags()))
                .returning()
                .fetchOne();

        dslContext.update(CONTENT)
                .set(CONTENT.ARTICLE_ID, articleRecord.getId())
                .where(CONTENT.ID.eq(contentRecord.getId()))
                .execute();

        return  articleRecord.getId();
    }

    public Long findContentIdByArticleId(Long articleId) {
        Long contentId = dslContext.select(Content.CONTENT.ID)
                .from(Article.ARTICLE)
                .join(Content.CONTENT).on(Article.ARTICLE.CONTENT_ID.eq(Content.CONTENT.ID))
                .where(Article.ARTICLE.ID.eq(articleId))
                .fetchOneInto(Long.class);

        if (contentId == null) {
            throw new ContentNotFoundException("Content not found for article id: " + articleId);
        }
        return contentId;
    }

    public void updateArticle(Long articleId, Long contentId, ArticleUpdateRequestDTO updateDTO) {
        dslContext.update(CONTENT)
                .set(CONTENT.TEXT, updateDTO.getContentText())
                .where(CONTENT.ID.eq(contentId))
                .execute();

        dslContext.update(ARTICLE)
                .set(ARTICLE.TITLE, updateDTO.getTitle())
                .set(ARTICLE.TAG_STRING, String.join(" ", updateDTO.getTags()))
                .where(ARTICLE.ID.eq(articleId))
                .execute();
    }


    public List<String> readArticleList(){
        return dslContext
                .select(Article.ARTICLE.TITLE)
                .from(Article.ARTICLE)
                .fetch(Article.ARTICLE.TITLE);
    }

    public Optional<String> readArticleById(Long articleId) {
        return dslContext.select(Content.CONTENT.TEXT)
                .from(ARTICLE)
                .join(CONTENT).on(ARTICLE.CONTENT_ID.eq(CONTENT.ID))
                .where(ARTICLE.ID.eq(articleId))
                .fetchOptionalInto(String.class);
    }

    public void deleteArticle(Long articleId) {
        Long contentId = findContentIdByArticleId(articleId);
        if (contentId != null) {
            dslContext.delete(CONTENT)
                    .where(CONTENT.ID.eq(contentId))
                    .execute();
        }
        dslContext.delete(ARTICLE)
                .where(ARTICLE.ID.eq(articleId))
                .execute();
    }
}
