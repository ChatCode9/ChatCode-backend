package com.chatcode.repository.follow;

import static com.chatcode.jooq.tables.Avatar.AVATAR;
import static com.chatcode.jooq.tables.Follow.FOLLOW;

import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Follow;
import com.chatcode.repository.ReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FollowReadRepository implements ReadRepository<Follow> {

    private final DSLContext dsl;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Follow> findById(long id) {
        return Optional.empty();
    }

    public Long countByFollower(Long avatarId) {
        return nativeQuery(em, dsl.selectCount()
                .from(FOLLOW)
                .where(FOLLOW.FOLLOWING_ID.eq(avatarId)), Long.class)
                .stream()
                .findFirst()
                .orElse(0L);
    }

    public Long countByFollowing(Long avatarId) {
        return nativeQuery(em, dsl.selectCount()
                .from(FOLLOW)
                .where(FOLLOW.FOLLOWER_ID.eq(avatarId)), Long.class)
                .stream()
                .findFirst()
                .orElse(0L);
    }


    public Long countByFollowingId(Long avatarId) {
        return nativeQuery(em, dsl.selectCount()
                .from(FOLLOW)
                .where(FOLLOW.FOLLOWING_ID.eq(avatarId)), Long.class)
                .stream()
                .findFirst()
                .orElse(0L);
    }

    public List<Avatar> findFollowersProfiles(Long avatarId) {
        return nativeQuery(em, dsl.select(AVATAR.fields())
                .from(FOLLOW)
                .join(AVATAR).on(FOLLOW.FOLLOWER_ID.eq(AVATAR.ID))
                .where(FOLLOW.FOLLOWING_ID.eq(avatarId)), Avatar.class);
    }

    public List<Avatar> findFollowingsProfiles(Long avatarId) {
        return nativeQuery(em, dsl.select(AVATAR.fields())
                .from(FOLLOW)
                .join(AVATAR).on(FOLLOW.FOLLOWING_ID.eq(AVATAR.ID))
                .where(FOLLOW.FOLLOWER_ID.eq(avatarId)), Avatar.class);
    }
}
