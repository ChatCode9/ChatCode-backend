package com.chatcode.repository.scrap;

import static com.chatcode.jooq.tables.Scrap.SCRAP;

import com.chatcode.domain.entity.scrap.Scrap;
import com.chatcode.repository.ReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

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

    public Optional<Scrap> findByAvatarIdAndArticleId(Long avatarId, Long articleId) {
        return em.createQuery("SELECT s FROM Scrap s WHERE s.avatarId.id = :avatarId AND s.articleId.id = :articleId", Scrap.class)
                .setParameter("avatarId", avatarId)
                .setParameter("articleId", articleId)
                .getResultList()
                .stream()
                .findFirst();
    }

    public List<Scrap> findByAvatarId(Long avatarId) {
        return em.createQuery("SELECT s FROM Scrap s WHERE s.avatarId.id = :avatarId", Scrap.class)
                .setParameter("avatarId", avatarId)
                .getResultList();
    }
    public List<Scrap> findAll() {
        return nativeQuery(em, dsl.select().from(SCRAP), Scrap.class);
    }
}
