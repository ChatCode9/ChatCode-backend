package com.chatcode.repository.tag;

import static com.chatcode.jooq.tables.InterestTag.INTEREST_TAG;
import static com.chatcode.jooq.tables.Role.ROLE;

import com.chatcode.domain.RoleType;
import com.chatcode.domain.entity.InterestTag;
import com.chatcode.domain.entity.Role;
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
public class InterestTagReadRepository implements ReadRepository<InterestTag> {
    private final DSLContext dsl;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<InterestTag> findById(long id) {
        return Optional.ofNullable(nativeQuery(em, dsl.select()
                .from(INTEREST_TAG)
                .where(INTEREST_TAG.ID.eq(id)), InterestTag.class)
                .get(0));
    }

    public Optional<InterestTag> findByName(String name) {
        List<InterestTag> interestTags = nativeQuery(em, dsl.select()
                .from(INTEREST_TAG)
                .where(INTEREST_TAG.NAME.eq(name)), InterestTag.class);
        return (interestTags.isEmpty()) ? Optional.empty() : Optional.of(interestTags.get(0));
    }

    public List<InterestTag> findAll() {
        return nativeQuery(em, dsl.select()
                .from(INTEREST_TAG), InterestTag.class);
    }
}
