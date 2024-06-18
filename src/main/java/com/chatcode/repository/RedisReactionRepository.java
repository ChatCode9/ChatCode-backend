package com.chatcode.repository;

import com.chatcode.domain.LikeableContentType;
import jakarta.annotation.Resource;

import java.util.Optional;

import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RedisReactionRepository {

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOps;

    static String KEY = "REACTION";
    static String LIKE = "LIKE";
    static String DISLIKE = "DISLIKE";


    public void addValue(LikeableContentType contentType, long contentId, long userId, boolean isLike) {
        String key = String.format("%s:%s:%s", KEY, contentType.name(), contentId);
        String value = String.format("%s:%s", isLike ? LIKE : DISLIKE, userId);
        setOps.add(key, value);
    }

    public Optional<Boolean> checkAlreadyReacted(LikeableContentType contentType, long contentId, long userId) {
        String key = String.format("%s:%s:%s", KEY, contentType.name(), contentId);
        return Optional.of(checkAlreadyLiked(contentType, contentId, userId) && checkAlreadyDisLiked(contentType, contentId, userId));
    }

    public Boolean checkAlreadyLiked(LikeableContentType contentType, long contentId, long userId) {
        String key = String.format("%s:%s:%s", KEY, contentType.name(), contentId);
        String value = String.format("%s:%s", LIKE, userId);
        return setOps.isMember(key, value);
    }

    public Boolean checkAlreadyDisLiked(LikeableContentType contentType, long contentId, long userId) {
        String key = String.format("%s:%s:%s", KEY, contentType.name(), contentId);
        String value = String.format("%s:%s", DISLIKE, userId);
        return setOps.isMember(key, value);
    }
}
