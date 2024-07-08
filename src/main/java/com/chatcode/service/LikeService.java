package com.chatcode.service;

import com.chatcode.domain.LikeableContentType;
import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Opinion;
import com.chatcode.dto.like.LikeRequest;
import com.chatcode.dto.like.Likeable;
import com.chatcode.exception.common.ResourceNotFoundException;
import com.chatcode.exception.reaction.AlreadyReactException;
import com.chatcode.repository.ReadRepository;
import com.chatcode.repository.RedisReactionRepository;
import com.chatcode.repository.WriteRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

  private final ReadRepository<Article> articleReadRepository;
  private final WriteRepository<Article> articleWriteRepository;
  private final ReadRepository<Opinion> opinionReadRepository;
  private final WriteRepository<Opinion> opinionWriteRepository;
  private final RedisReactionRepository redisReactionRepository;

  @Transactional
  public void like(LikeableContentType contentType, int contentId, int userId,
      LikeRequest likeRequest) {
    Likeable content = getContent(contentType, contentId);
    checkAlreadyLiked(contentType, contentId, userId);
    redisReactionRepository.addValue(contentType, contentId, 1);
    updateLikeCount(contentType, content, likeRequest);
  }

  private Likeable getContent(LikeableContentType contentType, int contentId) {
    return switch (contentType) {
      case ARTICLE -> fetchContent(articleReadRepository, contentId, contentType.name());
      case OPINION -> fetchContent(opinionReadRepository, contentId, contentType.name());
    };
  }

  private <T extends Likeable> T fetchContent(ReadRepository<T> repository, int contentId,
      String typeName) {
    return repository.findById(contentId).orElseThrow(() ->
        new ResourceNotFoundException(typeName + " not found with ID: " + contentId));
  }

  private void updateLikeCount(LikeableContentType contentType, Likeable content,
      LikeRequest likeRequest) {
    switch (contentType) {
      case ARTICLE:
        Article a = (Article) content;
        a.updateLikeCount(likeRequest);
        articleWriteRepository.save(a);
        break;
      case OPINION:
        Opinion op = (Opinion) content;
        op.updateLikeCount(likeRequest);
        opinionWriteRepository.save(op);
        break;
    }
  }

  private void checkAlreadyLiked(LikeableContentType contentType, long contentId, long userId) {
    Optional<Boolean> alreadyLiked = redisReactionRepository.checkAlreadyLiked(contentType,
        contentId, userId);
    alreadyLiked.filter(liked -> !liked)
        .orElseThrow(() -> new AlreadyReactException(
            "User " + userId + " has already reacted to content " + contentId));
  }
}
