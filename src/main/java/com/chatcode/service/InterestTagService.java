package com.chatcode.service;

import com.chatcode.domain.entity.InterestTag;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagNameRequest;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagRenameRequest;
import com.chatcode.dto.tag.InterestTagResponse;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.tag.InterestTagReadRepository;
import com.chatcode.repository.tag.InterestTagWriteRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class InterestTagService {

    private final InterestTagWriteRepository interestTagWriteRepository;
    private final InterestTagReadRepository interestTagReadRepository;

    @Transactional(readOnly = true)
    public List<InterestTagResponse> getAllInterestTags() {
        List<InterestTag> tags = interestTagReadRepository.findAll();
        return InterestTagResponse.of(tags);
    }

    @Transactional
    public List<InterestTagResponse> addInterestTags(List<InterestTagNameRequest> params) {
        List<InterestTag> tags = params.stream()
                        .map(tag -> interestTagReadRepository.findByName(tag.getName())
                                .orElse(InterestTagNameRequest.toEntity(tag)))
                        .collect(Collectors.toList());

        interestTagWriteRepository.saveAll(tags);
        return InterestTagResponse.of(tags);
    }

    @Transactional
    public void deleteInterestTags(Long params) {
        interestTagWriteRepository.deleteById(params);
    }

    @Transactional
    public List<InterestTagResponse> updateInterestTags(List<InterestTagRenameRequest> params) {
        List<InterestTag> tags = params.stream()
                        .map(tag -> {
                            InterestTag entity = interestTagReadRepository.findById(tag.getId())
                                    .orElseThrow(() -> new ContentNotFoundException("Interest tag not found"));
                            entity.setName(tag.getName());
                            return entity;
                        })
                        .toList();
        interestTagWriteRepository.saveAll(tags);
        return InterestTagResponse.of(tags);
    }
}
