package com.chatcode.repository.user;

import static com.chatcode.jooq.tables.Role.ROLE;
import static com.chatcode.jooq.tables.User.USER;
import static com.chatcode.jooq.tables.UserRole.USER_ROLE;

import com.chatcode.domain.entity.User;
import com.chatcode.jooq.tables.pojos.Role;
import com.chatcode.repository.ReadRepository;
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

    public List<User> findAll() {
        return dsl.selectFrom(USER).fetchInto(User.class);
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(dsl.selectFrom(USER).where(USER.ID.eq(id))
                .fetchOneInto(User.class));
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(dsl.selectFrom(USER).where(USER.USERNAME.eq(username))
                .fetchOneInto(User.class));
    }

    public List<String> findRolesById(Long userId) {
        return dsl.select(ROLE.AUTHORITY)
                .from(USER_ROLE)
                .join(ROLE).on(USER_ROLE.ROLE_ID.eq(ROLE.ID))
                .where(USER_ROLE.USER_ID.eq(userId))
                .fetchInto(String.class);
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
