package com.chatcode.repository.avatar;

import static com.chatcode.jooq.tables.Avatar.AVATAR;

import com.chatcode.domain.entity.Avatar;
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
public class AvatarReadRepository implements ReadRepository<Avatar> {

    private final DSLContext dsl;

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Avatar> findById(long id) {
        List<Avatar> avatars = nativeQuery(em, dsl.select()
                .from(AVATAR)
                .where(AVATAR.ID.eq(id)), Avatar.class);
        return (avatars.isEmpty()) ? Optional.empty() : Optional.of(avatars.get(0));
    }

    public List<Avatar> findAll() {
        return nativeQuery(em, dsl.select()
                .from(AVATAR), Avatar.class);
    }
}
