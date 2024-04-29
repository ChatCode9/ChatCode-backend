package com.chatcode.repository;

import jakarta.annotation.Resource;
import java.util.Optional;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisReactionRepository {

  @Resource(name = "redisTemplate")
  private SetOperations<String, String> setOps;

  static String KEY = "KEY";

  public void addValue(long contentId, long userId) {
    String key = String.format("%s-%s", KEY, contentId);
    String value = String.format("%s", userId);
    setOps.add(key, value);
  }

  public Optional<Boolean> checkAlreadyReact(long contentId, long userId) {
    String key = String.format("%s-%s", KEY, contentId);
    String value = String.format("%s", userId);
    return Optional.ofNullable(setOps.isMember(key, value));
  }
}
