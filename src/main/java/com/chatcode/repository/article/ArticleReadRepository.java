package com.chatcode.repository.article;

import static com.chatcode.jooq.tables.Article.ARTICLE;

import com.chatcode.domain.entity.Article;
import com.chatcode.repository.ReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleReadRepository implements ReadRepository<Article> {

  private final DSLContext dsl;

  @PersistenceContext
  private EntityManager em;

  @Override
  public Optional<Article> findById(long id) {
    return Optional.ofNullable(
        nativeQuery(em, dsl.select().from(ARTICLE).where(ARTICLE.ID.eq(id)), Article.class).get(0));
  }
}
