package com.chatcode.config.auth.oauth;

import com.chatcode.config.auth.LoginUser;
import com.chatcode.config.auth.oauth.dto.OAuth2Response;
import com.chatcode.config.auth.oauth.dto.UserDto;
import com.chatcode.jooq.tables.pojos.User;
import com.chatcode.repository.UserRepository;
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

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2Response oAuth2Response = OAuth2Response.of(
                getOAuthRegistrationId(userRequest),
                oAuth2User.getAttributes()
        );

        // fetch user
        User loginUser = userRepository.findByUsername(oAuth2Response.getUsername())
                .orElseGet(() -> signUpUser(oAuth2Response));

        List<String> roles = userRepository.findRolesById(loginUser.getId());

        return new LoginUser(loginUser, oAuth2User.getAttributes(), roles);
    }

    private String getOAuthRegistrationId(OAuth2UserRequest userRequest) {
        return userRequest.getClientRegistration().getRegistrationId();
    }

    private User signUpUser(OAuth2Response oAuth2Response) {
        // TODO: Add avatarId, createIp
        UserDto userDto = UserDto.builder()
                .avatarId(1L)
                .version(0L)
                .createIp("127.0.0.1")
                .username(oAuth2Response.getUsername())
                .status(0)
                .roles(List.of("ROLE_USER"))
                .build();
        return userRepository.create(userDto);
    }
}
