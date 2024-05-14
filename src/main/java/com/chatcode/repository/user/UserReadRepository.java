package com.chatcode.repository.user;

import static com.chatcode.jooq.tables.Role.ROLE;
import static com.chatcode.jooq.tables.User.USER;
import static com.chatcode.jooq.tables.UserRole.USER_ROLE;

import com.chatcode.domain.entity.User;
import com.chatcode.jooq.tables.pojos.Role;
import com.chatcode.repository.ReadRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserReadRepository implements ReadRepository<User> {

    private final DSLContext dsl;

    @PersistenceContext
    private final EntityManager em;

    public List<User> findAll() {
        return nativeQuery(em, dsl.select().from(USER), User.class);
    }

    @Override
    public Optional<User> findById(long id) {
        List<User> users = nativeQuery(em, dsl.select().from(USER).where(USER.ID.eq(id)), User.class);
        return (users.isEmpty()) ? Optional.empty() : Optional.of(users.get(0));
    }

    public Optional<User> findByUsername(String username) {
        List<User> users = nativeQuery(em, dsl.select().from(USER).where(USER.USERNAME.eq(username)), User.class);
        return (users.isEmpty()) ? Optional.empty() : Optional.of(users.get(0));
    }

    public List<String> findRolesById(Long userId) {
        return nativeQuery(em, dsl.select(ROLE.AUTHORITY)
                .from(USER_ROLE)
                .join(ROLE).on(USER_ROLE.ROLE_ID.eq(ROLE.ID))
                .where(USER_ROLE.USER_ID.eq(userId)), String.class);
    }

    public Map<User, List<Role>> findByUsernameWithRoles(String username) {
        return dsl.select(USER.fields())
                .select(ROLE.fields())
                .from(USER)
                .join(USER_ROLE).on(USER.ID.eq(USER_ROLE.USER_ID))
                .join(ROLE).on(USER_ROLE.ROLE_ID.eq(ROLE.ID))
                .where(USER.USERNAME.eq(username))
                .fetchGroups(
                        r -> r.into(USER).into(User.class),
                        r -> r.into(ROLE).into(Role.class)
                );
    }
}
