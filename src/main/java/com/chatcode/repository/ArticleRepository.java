package com.chatcode.repository;

import com.chatcode.exception.common.ResourceNotFoundException;
import com.chatcode.jooq.tables.pojos.Article;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

import static com.chatcode.jooq.tables.Article.ARTICLE;

@Repository
public class ArticleRepository {

  @Autowired
  private DSLContext dsl;

  public Article findById(long id) {
    return dsl.selectFrom(ARTICLE).where(ARTICLE.ID.eq(id))
        .fetchOneInto(Article.class);
  }

  public int like(long id) {
    Optional<Integer> likeCount = dsl.select(ARTICLE.LIKE_COUNT).from(ARTICLE).where(ARTICLE.ID.eq(id))
        .fetchOneInto(Optional.class);
    int like = Objects.requireNonNull(likeCount).orElseThrow(ResourceNotFoundException::new);

    return dsl.update(ARTICLE).set(ARTICLE.LIKE_COUNT, like + 1).execute();
  }

  public int dislike(long id) {
    Optional<Integer> dislikeCount = dsl.select(ARTICLE.DISLIKE_COUNT).from(ARTICLE).where(ARTICLE.ID.eq(id))
        .fetchOneInto(Optional.class);
    int dislike = Objects.requireNonNull(dislikeCount).orElseThrow(ResourceNotFoundException::new);

    return dsl.update(ARTICLE).set(ARTICLE.LIKE_COUNT, dislike + 1).execute();
  }
}
