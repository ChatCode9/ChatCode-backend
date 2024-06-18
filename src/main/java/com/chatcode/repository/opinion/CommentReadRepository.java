package com.chatcode.repository.opinion;

import com.chatcode.domain.entity.Comment;
import com.chatcode.dto.comment.CommentVo;
import com.chatcode.repository.ReadRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.chatcode.jooq.Tables.AVATAR;
import static com.chatcode.jooq.Tables.COMMENT;
import static org.jooq.impl.DSL.and;

@Repository
@RequiredArgsConstructor
public class CommentReadRepository implements ReadRepository<Comment> {
    private final DSLContext dsl;

    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(dsl.selectFrom(COMMENT).where(COMMENT.ID.eq(id))
                .fetchOneInto(Comment.class));
    }

    public List<CommentVo> retrieve(long articleId) {
        return dsl.select(
                        COMMENT.ID,
                        COMMENT.ARTICLE_ID,
                        COMMENT.AUTHOR_ID,
                        AVATAR.NICKNAME,
                        AVATAR.PICTURE,
                        COMMENT.DATE_CREATED,
                        COMMENT.PARENT_ID,
                        COMMENT.LIKE_COUNT,
                        COMMENT.DISLIKE_COUNT,
                        COMMENT.DEPTH,
                        COMMENT.ANCESTOR_ID
                ).from(COMMENT)
                .join(AVATAR).on(COMMENT.AUTHOR_ID.eq(AVATAR.ID))
                .where(COMMENT.ARTICLE_ID.eq(articleId))
                .fetchInto(CommentVo.class);
    }

    private Condition getArticleRetrieveCondition(long articleId, Long parentId) {
        Condition result = and(COMMENT.ARTICLE_ID.eq(articleId));

        if (parentId == null) {
            return result.and(COMMENT.PARENT_ID.isNull());
        }

        return result.and(COMMENT.PARENT_ID.eq(parentId));
    }
}
