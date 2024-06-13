package com.chatcode.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "avatar", fetch = jakarta.persistence.FetchType.LAZY)
    private List<InterestTag> interestTags;

    public void addInterestTag(InterestTag interestTag) {
        if (interestTags == null) {
            interestTags = new ArrayList<>();
        }
        interestTags.add(interestTag);
    }

    public void removeInterestTag(InterestTag interestTag) {
        if (interestTags != null) {
            interestTags.remove(interestTag);
        }
    }
}