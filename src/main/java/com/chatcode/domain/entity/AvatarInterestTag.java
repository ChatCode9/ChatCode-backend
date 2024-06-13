package com.chatcode.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Data
@Entity
public class AvatarInterestTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @ManyToOne
    @JoinColumn(name = "interest_tag_id")
    private InterestTag interestTag;

    public static AvatarInterestTag of(Avatar avatar, InterestTag interestTag) {
        AvatarInterestTag avatarInterestTag = new AvatarInterestTag();
        avatarInterestTag.avatar = avatar;
        avatarInterestTag.interestTag = interestTag;
        return avatarInterestTag;
    }
}
