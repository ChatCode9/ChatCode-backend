package com.chatcode.service;

import com.chatcode.dto.User;
import com.chatcode.exception.common.ResourceNotFoundException;
import com.chatcode.exception.reaction.AlreadyReactException;
import com.chatcode.jooq.tables.pojos.Article;
import com.chatcode.repository.ArticleRepository;
import com.chatcode.repository.RedisReactionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JooqArticleService {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private RedisReactionRepository redisReactionRepository;

  @Transactional
  public void like(int id, User user){
    //Todo 실제 userId 넣어주기
    Article article = this.validateArticleAndCheckReaction(id, 1);
    redisReactionRepository.addValue(article.getId(), 1);

    articleRepository.like(article.getId());
  }

  @Transactional
  public void dislike(int id, User user){
    //Todo 실제 userId 넣어주기
    Article article = this.validateArticleAndCheckReaction(id, 1);
    redisReactionRepository.addValue(article.getId(), 1);

    articleRepository.dislike(article.getId());
  }

  private Article validateArticleAndCheckReaction(int articleId, long userId) {
    Article article = articleRepository.findById(articleId);
    if (article == null) {
      throw new ResourceNotFoundException("Article not found with ID: " + articleId);
    }

    Optional<Boolean> alreadyReacted = redisReactionRepository.checkAlreadyReact(article.getId(), userId);
    if (alreadyReacted.isEmpty() || alreadyReacted.get()) {
      throw new AlreadyReactException("User " + userId + " has already reacted to article " + articleId);
    }

    return article;
  }
}
