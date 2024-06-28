package com.chatcode.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "avatar")
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    private Long version;

    @Column(nullable = false)
    private Integer activityPoint;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = true)
    private String content;

    @OneToMany(mappedBy = "avatar", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<AvatarInterestTag> avatarInterestTags = new ArrayList<>();

    public void addInterestTag(InterestTag interestTag) {
        if (avatarInterestTags.stream().noneMatch(
                avatarInterestTag -> interestTag.getId().equals(avatarInterestTag.getInterestTag().getId()))) {
            avatarInterestTags.add(AvatarInterestTag.of(this, interestTag));
        }
    }

    public void addInterestTags(List<InterestTag> interestTags) {
        interestTags.forEach(this::addInterestTag);
    }

    public void removeInterestTag(InterestTag interestTag) {
        avatarInterestTags.removeIf(
                avatarInterestTag -> interestTag.getId().equals(avatarInterestTag.getInterestTag().getId()));
    }

    public void removeAllInterestTags() {
        avatarInterestTags.clear();
    }
}