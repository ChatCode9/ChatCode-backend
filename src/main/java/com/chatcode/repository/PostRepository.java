package com.chatcode.repository;

import static com.chatcode.jooq.Tables.ARTICLE;
import static com.chatcode.jooq.Tables.CONTENT;
import static org.jooq.impl.DSL.currentLocalDateTime;

import com.chatcode.dto.PostRequestDTO.PostCreateRequestDTO;
import com.chatcode.dto.PostRequestDTO.PostUpdateRequestDTO;
import com.chatcode.dto.PostResponseDTO.PostCreateResponseDTO;
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
public class PostRepository {
    private final DSLContext dslContext;

    public PostCreateResponseDTO createPost(PostCreateRequestDTO postDTO) {

        ContentRecord contentRecord;
        contentRecord = dslContext
                .insertInto(CONTENT)
                .set(CONTENT.TEXT, postDTO.getContentText())
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
                .set(ARTICLE.TITLE, postDTO.getTitle())
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
                .returning()
                .fetchOne();

        return PostCreateResponseDTO.builder()
                .id(contentRecord.getId())
                .title(articleRecord.getTitle())
                .contentText(contentRecord.getText())
                .version(contentRecord.getVersion())
                .build();
    }

    public Long findContentIdByArticleId(Long articleId) {
        return dslContext.select(Content.CONTENT.ID)
                .from(Article.ARTICLE)
                .join(Content.CONTENT).on(Article.ARTICLE.CONTENT_ID.eq(Content.CONTENT.ID))
                .where(Article.ARTICLE.ID.eq(articleId))
                .fetchOneInto(Long.class);
    }

    public static class ContentNotFoundException extends RuntimeException {
        public ContentNotFoundException(String message) {
            super(message);
        }
    }

    public void updatePost(Long articleId, Long contentId, PostUpdateRequestDTO updateDTO) {
        dslContext.update(CONTENT)
                .set(CONTENT.TEXT, updateDTO.getContentText())
                .where(CONTENT.ID.eq(contentId))
                .execute();

        dslContext.update(ARTICLE)
                .set(ARTICLE.TITLE, updateDTO.getTitle())
                .where(ARTICLE.ID.eq(articleId))
                .execute();
    }


    public List<String> readPostList(){
        return dslContext
                .select(Article.ARTICLE.TITLE)
                .from(Article.ARTICLE)
                .fetch(Article.ARTICLE.TITLE);
    }

    public Optional<String> readPostById(Long articleId) {
        return dslContext.select(Content.CONTENT.TEXT)
                .from(ARTICLE)
                .join(CONTENT).on(ARTICLE.CONTENT_ID.eq(CONTENT.ID))
                .where(ARTICLE.ID.eq(articleId))
                .fetchOptionalInto(String.class);
    }

    public void deletePost(Long articleId) {
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
