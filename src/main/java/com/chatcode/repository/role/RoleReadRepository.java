package com.chatcode.repository.role;

import static com.chatcode.jooq.tables.Role.ROLE;

import com.chatcode.domain.RoleType;
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
public class RoleReadRepository implements ReadRepository<Role> {
    private final DSLContext dsl;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Role> findById(long id) {
        return Optional.ofNullable(dsl.selectFrom(ROLE).where(ROLE.ID.eq(id))
                .fetchOneInto(Role.class));
    }

    public Optional<Role> findByRole(RoleType authority) {
        List<Role> roles = nativeQuery(em, dsl.select()
                .from(ROLE)
                .where(ROLE.AUTHORITY.eq(authority.getValue())), Role.class);
        return (roles.isEmpty()) ? Optional.empty() : Optional.of(roles.get(0));
    }
}
