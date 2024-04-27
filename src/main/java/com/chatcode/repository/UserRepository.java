package com.chatcode.repository;

import static com.chatcode.jooq.tables.Role.ROLE;
import static com.chatcode.jooq.tables.User.USER;
import static com.chatcode.jooq.tables.UserRole.USER_ROLE;
import static org.jooq.impl.DSL.currentLocalDateTime;

import com.chatcode.config.auth.oauth.dto.UserDto;
import com.chatcode.jooq.tables.pojos.Role;
import com.chatcode.jooq.tables.pojos.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final DSLContext dsl;

    public List<User> findAll() {
        return dsl.selectFrom(USER).fetchInto(User.class);
    }

    public Optional<User> findByUsername(String username) {
        return dsl.selectFrom(USER)
                .where(USER.USERNAME.eq(username))
                .fetchOptionalInto(User.class);
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

    public List<Role> findRolesByUsername(String username) {
        return dsl.select()
                .from(USER_ROLE)
                .join(USER).on(USER_ROLE.USER_ID.eq(USER.ID))
                .join(ROLE).on(USER_ROLE.ROLE_ID.eq(ROLE.ID))
                .where(USER.USERNAME.eq(username))
                .fetchInto(Role.class);
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
