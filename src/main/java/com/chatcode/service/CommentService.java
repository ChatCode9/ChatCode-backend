package com.chatcode.service;

import com.chatcode.domain.LikeableContentType;
import com.chatcode.dto.comment.response.CommentResponseDto;
import com.chatcode.repository.RedisReactionRepository;
import com.chatcode.repository.opinion.CommentReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentReadRepository repository;
    private final RedisReactionRepository redisReactionRepository;

    public List<CommentResponseDto> getComments(long articleId, long avatarId) {
        return repository.retrieve(articleId).stream().map(comment ->
                CommentResponseDto.of(comment, getIsLiked(comment.getCommentId(), avatarId),
                        getIsRole(avatarId, comment.getUserId()))
        ).toList();
    }

    private Boolean getIsLiked(long opinionId, long userId) {
        boolean isDisLiked = redisReactionRepository.checkAlreadyDisLiked(LikeableContentType.OPINION, opinionId,
                userId);
        boolean isLiked = redisReactionRepository.checkAlreadyDisLiked(LikeableContentType.OPINION, opinionId, userId);

        if (!isLiked && !isDisLiked) {
            return null;
        } else {
            return isLiked;
        }
    }

    private Boolean getIsRole(long userId, long authorId) {
        return userId == authorId;
    }

//    public Void comment(long avatarId, long articleId {
//
//    }
}
