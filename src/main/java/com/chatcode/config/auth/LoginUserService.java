package com.chatcode.config.auth;

import com.chatcode.domain.entity.User;
import com.chatcode.repository.user.UserReadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginUserService implements UserDetailsService {

    private final UserReadRepository userReadRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userReadRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " 인증 실패"));
        List<String> roles = userReadRepository.findRolesById(user.getId());

        return new LoginUser(user, null, roles);
    }
}
