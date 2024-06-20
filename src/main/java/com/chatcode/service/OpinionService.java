package com.chatcode.service;

import com.chatcode.domain.LikeableContentType;
import com.chatcode.dto.opinion.response.OpinionResponseDto;
import com.chatcode.repository.RedisReactionRepository;
import com.chatcode.repository.opinion.OpinionReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionReadRepository repository;
    private final RedisReactionRepository redisReactionRepository;

    public List<OpinionResponseDto> getOpinions(long articleId, long avatarId) {
        return repository.retrieve(articleId).stream().map(opinion ->
                OpinionResponseDto.of(opinion, getIsLiked(opinion.getId(), avatarId),
                        getIsRole(avatarId, opinion.getUserId()))
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
