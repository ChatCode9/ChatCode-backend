package com.chatcode.repository.user;

import static com.chatcode.jooq.tables.Role.ROLE;
import static com.chatcode.jooq.tables.User.USER;
import static com.chatcode.jooq.tables.UserRole.USER_ROLE;
import static org.jooq.impl.DSL.currentLocalDateTime;

import com.chatcode.config.auth.oauth.dto.UserDto;
import com.chatcode.domain.entity.User;
import com.chatcode.jooq.tables.pojos.Role;
import com.chatcode.repository.ReadRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public User create(UserDto userDto) {
        User user = dsl.insertInto(USER)
                .set(USER.AVATAR_ID, userDto.avatarId())
                .set(USER.VERSION, userDto.version())
                .set(USER.CREATE_IP, userDto.createIp())
                .set(USER.LAST_UPDATE_IP, userDto.createIp())
                .set(USER.DATE_CREATED, currentLocalDateTime())
                .set(USER.LAST_UPDATED, currentLocalDateTime())
                .set(USER.USERNAME, userDto.username())
                .set(USER.STATUS, userDto.status())
                .set(USER.WITHDRAW, false)
                .returning()
                .fetchOne()
                .into(User.class);

        List<Long> roleIds = dsl.select(ROLE.ID)
                .from(ROLE)
                .where(ROLE.AUTHORITY.in(userDto.roles()))
                .fetch(ROLE.ID);

        for (Long roleId : roleIds) {
            dsl.insertInto(USER_ROLE)
                    .set(USER_ROLE.USER_ID, user.getId())
                    .set(USER_ROLE.ROLE_ID, roleId)
                    .execute();
        }
        return user;
    }
}
