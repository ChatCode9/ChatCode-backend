package com.chatcode.repository;

import com.chatcode.domain.LikeableContentType;
import jakarta.annotation.Resource;

import java.util.Optional;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisReactionRepository {

    @Resource(name = "redisTemplate")
    private SetOperations<String, Long> setOps;

    static String KEY = "REACTION";

    public void addValue(LikeableContentType contentType, long contentId, long userId) {
        String key = String.format("%s:%s:%s", KEY, contentType.name(), contentId);
        setOps.add(key, userId);
    }

    public Optional<Boolean> checkAlreadyLiked(LikeableContentType contentType, long contentId, long userId) {
        String key = String.format("%s:%s:%s", KEY, contentType.name(), contentId);
        return Optional.ofNullable(setOps.isMember(key, userId));
    }
}
