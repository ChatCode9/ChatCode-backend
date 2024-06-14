package com.chatcode.config.dummy;

import com.chatcode.config.auth.enums.Status;
import com.chatcode.domain.RoleType;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Role;
import com.chatcode.domain.entity.User;
import java.util.List;

/*
id,avatar_id,version,create_ip,date_created,date_withdraw,last_update_ip,last_updated,username,withdraw,status
1,1,0,127.0.0.1,"2024-06-14 11:41:10",NULL,127.0.0.1,"2024-06-14 11:41:10",admin,0,0
2,2,0,127.0.0.1,"2024-06-14 11:41:22",NULL,127.0.0.1,"2024-06-14 11:41:22",test1,0,0
3,3,0,127.0.0.1,"2024-06-14 11:54:43",NULL,127.0.0.1,"2024-06-14 11:54:43",test2,0,0
4,4,0,127.0.0.1,"2024-06-14 11:57:22",NULL,127.0.0.1,"2024-06-14 11:57:22",test3,0,0
5,5,0,127.0.0.1,"2024-06-14 11:58:03",NULL,127.0.0.1,"2024-06-14 11:58:03",test4,0,0
6,6,0,127.0.0.1,"2024-06-14 11:58:11",NULL,127.0.0.1,"2024-06-14 11:58:11",test5,0,0
* */
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
}
