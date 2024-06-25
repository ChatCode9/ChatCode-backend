package com.chatcode.repository.tag;

import static com.chatcode.jooq.tables.AvatarInterestTag.AVATAR_INTEREST_TAG;

import com.chatcode.domain.entity.AvatarInterestTag;
import com.chatcode.domain.entity.InterestTag;
import com.chatcode.repository.ReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AvatarInterestTagReadRepository implements ReadRepository<AvatarInterestTag> {

    private final DSLContext dsl;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<AvatarInterestTag> findById(long id) {
        return Optional.ofNullable(nativeQuery(em, dsl.select()
                .from(AVATAR_INTEREST_TAG)
                .where(AVATAR_INTEREST_TAG.ID.eq(id)), AvatarInterestTag.class)
                .get(0));
    }

    public List<InterestTag> findInterestTagByAvatarId(Long avatarId) {
        return nativeQuery(em, dsl.select()
                .from(AVATAR_INTEREST_TAG)
                .where(AVATAR_INTEREST_TAG.AVATAR_ID.eq(avatarId)), InterestTag.class);
    }
}
