package com.chatcode.repository.opinion;

import com.chatcode.domain.entity.Opinion;
import com.chatcode.dto.opinion.OpinionVo;
import com.chatcode.repository.ReadRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.chatcode.jooq.Tables.AVATAR;
import static com.chatcode.jooq.Tables.OPINION;
import static org.jooq.impl.DSL.and;

@Repository
@RequiredArgsConstructor
public class OpinionReadRepository implements ReadRepository<Opinion> {
    private final DSLContext dsl;

    public Optional<Opinion> findById(long id) {
        return Optional.ofNullable(dsl.selectFrom(OPINION).where(OPINION.ID.eq(id))
                .fetchOneInto(Opinion.class));
    }

    public List<OpinionVo> retrieve(long articleId) {
        return dsl.select(
                        OPINION.ID,
                        OPINION.ARTICLE_ID,
                        OPINION.AUTHOR_ID,
                        AVATAR.NICKNAME,
                        AVATAR.PICTURE,
                        OPINION.DATE_CREATED,
                        OPINION.PARENT_ID,
                        OPINION.LIKE_COUNT,
                        OPINION.DISLIKE_COUNT,
                        OPINION.DEPTH,
                        OPINION.ANCESTOR_ID
                ).from(OPINION)
                .join(AVATAR).on(OPINION.AUTHOR_ID.eq(AVATAR.ID))
                .where(OPINION.ARTICLE_ID.eq(articleId))
                .fetchInto(OpinionVo.class);
    }

    private Condition getArticleRetrieveCondition(long articleId, Long parentId) {
        Condition result = and(OPINION.ARTICLE_ID.eq(articleId));

        if (parentId == null) {
            return result.and(OPINION.PARENT_ID.isNull());
        }

        return result.and(OPINION.PARENT_ID.eq(parentId));
    }
}
