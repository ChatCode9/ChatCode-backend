package com.chatcode.service;

import com.chatcode.domain.entity.Avatar;
import com.chatcode.dto.avatar.AvatarRequest.AvatarCreateRequest;
import com.chatcode.dto.avatar.AvatarRequest.AvatarUpdateRequest;
import com.chatcode.dto.avatar.AvatarResponse;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.avatar.AvatarReadRepository;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AvatarService {

    private final AvatarReadRepository avatarReadRepository;
    private final AvatarWriteRepository avatarWriteRepository;

    @Transactional
    public AvatarResponse createNewAvatar(AvatarCreateRequest params) {
        Avatar avatar = Avatar.builder()
                .activityPoint(params.getActivityPoint())
                .nickname(params.getNickname())
                .picture(params.getPicture())
                .build();
        avatarWriteRepository.save(avatar);
        return AvatarResponse.of(avatar);
    }

    @Transactional
    public AvatarResponse updateAvatar(Long avatarId, AvatarUpdateRequest params) {
        Avatar avatar = avatarReadRepository.findById(avatarId)
                .orElseThrow(() -> new ContentNotFoundException("Avatar not found"));

        if (params.getNickname() != null) {
            avatar.setNickname(params.getNickname());
        }
        if (params.getPicture() != null) {
            avatar.setPicture(params.getPicture());
        }

        avatarWriteRepository.save(avatar);
        return AvatarResponse.of(avatar);
    }

    @Transactional
    public void deleteAvatar(Long id) {
        try {
            Avatar avatar = avatarWriteRepository.getReferenceById(id);
            avatarWriteRepository.delete(avatar);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException("Avatar not found");
        }
    }

    @Transactional(readOnly = true)
    public List<AvatarResponse> getAllAvatars() {
        return avatarReadRepository.findAll().stream()
                .map(AvatarResponse::of)
                .toList();
    }

    @Transactional(readOnly = true)
    public AvatarResponse getOneAvatar(Long id) {
        Avatar avatar = avatarReadRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("Avatar not found"));
        return AvatarResponse.of(avatar);
    }
}
