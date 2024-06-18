package com.chatcode.repository.opinion;

import static com.chatcode.jooq.tables.Opinion.OPINION;

import com.chatcode.domain.entity.Comment;
import com.chatcode.repository.ReadRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OpinionReadRepository implements ReadRepository<Comment> {
    private final DSLContext dsl;

    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(dsl.selectFrom(OPINION).where(OPINION.ID.eq(id))
                .fetchOneInto(Comment.class));
    }

}
