package com.chatcode.service;

import static com.chatcode.exception.ExceptionCode.NOT_FOUND_AVATAR_ID;

import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Follow;
import com.chatcode.dto.avatar.AvatarResponse;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.exception.common.ResourceNotFoundException;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import com.chatcode.repository.follow.FollowReadRepository;
import com.chatcode.repository.follow.FollowWriteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowReadRepository followReadRepository;
    private final FollowWriteRepository followWriteRepository;
    private final AvatarWriteRepository avatarWriteRepository;

    @Transactional
    public void follow(Long followerId, Long followingId) {
        try {
            Avatar follower = avatarWriteRepository.getReferenceById(followerId);
            Avatar following = avatarWriteRepository.getReferenceById(followingId);
            followWriteRepository.save(Follow.of(follower, following));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(NOT_FOUND_AVATAR_ID);
        }
    }

    @Transactional
    public void unfollow(Long followerId, Long followingId) {
        Follow follow = followWriteRepository.getReferenceById(new Follow.PK(followerId, followingId));
        followWriteRepository.delete(follow);
    }

    public Long getNumberOfFollowers(Long avatarId) {
        return followReadRepository.countByFollowingId(avatarId);
    }

    public Long getNumberOfFollowings(Long avatarId) {
        return followReadRepository.countByFollower(avatarId);
    }

    public List<AvatarResponse> getFollowersProfiles(Long avatarId) {
        List<Avatar> followersProfiles = followReadRepository.findFollowersProfiles(avatarId);
        return AvatarResponse.of(followersProfiles);
    }

    public List<AvatarResponse> getFollowingsProfiles(Long avatarId) {
        List<Avatar> followingsProfiles = followReadRepository.findFollowingsProfiles(avatarId);
        return AvatarResponse.of(followingsProfiles);
    }
}
