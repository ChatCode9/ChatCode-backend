package com.chatcode.repository.article;

import static com.chatcode.handler.exception.ExceptionCode.NOT_FOUND_CATEGORY_NAME;
import static com.chatcode.jooq.tables.Article.ARTICLE;
import static com.chatcode.jooq.tables.Avatar.AVATAR;
import static com.chatcode.jooq.tables.Content.CONTENT;
import static org.jooq.impl.DSL.noCondition;

import com.chatcode.domain.article.ArticleVo;
import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Category;
import com.chatcode.dto.article.ArticleRetrieveServiceDto;
import com.chatcode.handler.exception.common.ResourceNotFoundException;
import com.chatcode.repository.ReadRepository;
import com.chatcode.repository.category.CategoryReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleReadRepository implements ReadRepository<Article> {

    private final DSLContext dsl;
    private final CategoryReadRepository categoryReadRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Article> findById(long id) {
        return Optional.ofNullable(nativeQuery(em, dsl.select().from(ARTICLE).where(ARTICLE.ID.eq(id)),
                Article.class).get(0));
    }

    public ArticleVo findArticleById(Long id) {
        return dsl.select(
                        ARTICLE.ID,
                        ARTICLE.DATE_CREATED,
                        AVATAR.PICTURE,
                        AVATAR.NICKNAME,
                        ARTICLE.TITLE,
                        CONTENT.TEXT,
                        ARTICLE.TAG_STRING,
                        ARTICLE.ENABLED,
                        ARTICLE.VIEW_COUNT,
                        ARTICLE.LIKE_COUNT
                )
                .from(ARTICLE)
                .join(AVATAR).on(ARTICLE.AUTHOR_ID.eq(AVATAR.ID))
                .join(CONTENT).on(ARTICLE.CONTENT_ID.eq(CONTENT.ID))
                .where(ARTICLE.ID.eq(id))
                .fetchOneInto(ArticleVo.class);
    }

    public List<ArticleVo> retrieve(ArticleRetrieveServiceDto query) {
        return dsl.select(
                        ARTICLE.ID,
                        ARTICLE.DATE_CREATED,
                        AVATAR.PICTURE,
                        AVATAR.NICKNAME,
                        ARTICLE.TITLE,
                        CONTENT.TEXT,
                        ARTICLE.TAG_STRING,
                        ARTICLE.ENABLED,
                        ARTICLE.VIEW_COUNT,
                        ARTICLE.LIKE_COUNT
                )
                .from(ARTICLE)
                .join(AVATAR).on(ARTICLE.AUTHOR_ID.eq(AVATAR.ID))
                .join(CONTENT).on(ARTICLE.CONTENT_ID.eq(CONTENT.ID))
                .where(condition(query))
                .orderBy(query.getSortBy().getDirection())
                .limit(query.getPageInfo().getSize())
                .offset(query.getPageInfo().getOffset())
                .fetchInto(ArticleVo.class);
    }

    public Long getTotalElements(ArticleRetrieveServiceDto query) {
        return dsl.selectCount(
                )
                .from(ARTICLE)
                .join(AVATAR).on(ARTICLE.AUTHOR_ID.eq(AVATAR.ID))
                .join(CONTENT).on(ARTICLE.CONTENT_ID.eq(CONTENT.ID))
                .where(condition(query))
                .fetchOneInto(Long.class);
    }

    public Condition condition(ArticleRetrieveServiceDto request) {

        Condition result = noCondition();

        if (request.getSearch() != null) {
            result = result.and(ARTICLE.TITLE.like("%" + request.getSearch() + "%"));
        }

        if (request.getStatus() != null) {
            result = result.and(ARTICLE.COMPLETED.eq(request.getStatus().isValue()));
        }

        if (request.getCategory() != null) {
            Category requestCategory = categoryReadRepository.findByName(request.getCategory().name())
                    .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_CATEGORY_NAME));
            result = result.and(ARTICLE.CATEGORY_ID.eq(requestCategory.getId()));
        }

        return result;
    }

    public boolean isAuthor(Long articleId, Long avatarId) {
        return dsl.selectCount()
                .from(ARTICLE)
                .where(ARTICLE.ID.eq(articleId).and(ARTICLE.AUTHOR_ID.eq(avatarId)))
                .fetchOneInto(Long.class) > 0;
    }
}
