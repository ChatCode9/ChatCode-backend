package com.chatcode.config.auth.oauth;

import static com.chatcode.domain.RoleType.USER;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.config.auth.oauth.dto.OAuth2Response;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Role;
import com.chatcode.domain.entity.User;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import com.chatcode.repository.role.RoleReadRepository;
import com.chatcode.repository.role.RoleWriteRepository;
import com.chatcode.repository.user.UserReadRepository;
import com.chatcode.repository.user.UserWriteRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OAuth2LoginUserService extends DefaultOAuth2UserService {

    private static final Integer INITIAL_ACTIVITY_POINT = 0;
    private static final Integer INITIAL_STATUS = 0;

    private final UserReadRepository userReadRepository;
    private final UserWriteRepository userWriteRepository;
    private final RoleReadRepository roleReadRepository;
    private final RoleWriteRepository roleWriteRepository;
    private final AvatarWriteRepository avatarWriteRepository;

    private Role userRoleType;

    @PostConstruct
    public void init() {
        userRoleType = roleReadRepository.findByRole(USER)
                .orElseGet(() -> roleWriteRepository.save(new Role(USER)));
    }


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Response oAuth2Response = OAuth2Response.of(
                getOAuthRegistrationId(userRequest),
                oAuth2User.getAttributes()
        );

        // fetch user
        User loginUser = userReadRepository.findByUsername(oAuth2Response.getUsername())
                .orElseGet(() -> signUpUser(oAuth2Response));

        List<String> roles = userReadRepository.findRolesById(loginUser.getId());

        return new LoginUser(loginUser, oAuth2User.getAttributes(), roles);
    }

    private String getOAuthRegistrationId(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration().getRegistrationId();
    }

    private User signUpUser(OAuth2Response oAuth2Response) {
        String ip = "127.0.0.1"; // TODO: IP
        Avatar avatar = avatarWriteRepository.save(Avatar.builder()
                .activityPoint(INITIAL_ACTIVITY_POINT)
                .nickname(oAuth2Response.name())
                .picture(oAuth2Response.profile())
                .build());
        User signUpUser = User.builder()
                .avatar(avatar)
                .createIp(ip)
                .lastUpdateIp(ip)
                .username(oAuth2Response.getUsername())
                .status(INITIAL_STATUS)
                .withdraw(false)
                .build();
        signUpUser.addRole(userRoleType);
        return userWriteRepository.save(signUpUser);
    }
}
