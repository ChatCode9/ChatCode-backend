package com.chatcode.repository.scrap;

import com.chatcode.domain.entity.Scrap;
import com.chatcode.dto.ScrapResponseDto;
import com.chatcode.repository.ReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.chatcode.jooq.tables.Article.ARTICLE;
import static com.chatcode.jooq.tables.Scrap.SCRAP;

@Repository
@RequiredArgsConstructor
public class ScrapReadRepository implements ReadRepository<Scrap> {

    private final DSLContext dsl;
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Scrap> findById(long id) {
        return Optional.ofNullable(em.find(Scrap.class, id));
    }

    public List<ScrapResponseDto> getScrapList(Long avatarId) {
        return dsl.select(SCRAP.ARTICLE_ID, ARTICLE.TITLE.as("articleTitle"), SCRAP.DATE_CREATED)
                .from(SCRAP)
                .join(ARTICLE).on(SCRAP.ARTICLE_ID.eq(ARTICLE.ID))
                .where(SCRAP.AVATAR_ID.eq(avatarId))
                .fetchInto(ScrapResponseDto.class);
    }

    public Boolean existsByAvatarIdAndArticleId(long avatarId, long articleId) {
        return dsl.selectOne().from(SCRAP).where(SCRAP.AVATAR_ID.eq(avatarId)).and(SCRAP.ARTICLE_ID.eq(articleId)).fetchOne() != null;
    }
}