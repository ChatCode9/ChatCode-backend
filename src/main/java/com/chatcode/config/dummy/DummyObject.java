package com.chatcode.config.dummy;

import com.chatcode.config.auth.enums.Status;
import com.chatcode.domain.RoleType;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.InterestTag;
import com.chatcode.domain.entity.Role;
import com.chatcode.domain.entity.User;
import java.util.List;

public class DummyObject {
    protected Avatar newAvatar(String nickname) {
        return Avatar.builder()
                .nickname(nickname)
                .picture("https://api.dicebear.com/8.x/lorelei/svg?seed=" + nickname)
                .activityPoint(0)
                .build();
    }
    protected User newUser(String username, Avatar avatar, List<Role> roles) {
        User user = User.builder()
                .avatar(avatar)
                .createIp("")
                .lastUpdateIp("")
                .username(username)
                .withdraw(false)
                .status(Status.NO_PROFILE)
                .build();
        roles.forEach(user::addRole);

        return user;
    }

    protected InterestTag newInterestTag(String name) {
        return InterestTag.builder()
                .name(name)
                .build();
    }
}
