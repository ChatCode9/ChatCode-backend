package com.chatcode.config.dummy;

import com.chatcode.domain.RoleType;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Role;
import com.chatcode.domain.entity.User;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import com.chatcode.repository.role.RoleReadRepository;
import com.chatcode.repository.role.RoleWriteRepository;
import com.chatcode.repository.user.UserReadRepository;
import com.chatcode.repository.user.UserWriteRepository;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class DummyDevInit extends DummyObject {

    private final EntityManager em;

    @Bean
    CommandLineRunner dummyUsers(
            UserWriteRepository userWriteRepository,
            UserReadRepository userReadRepository,
            RoleWriteRepository roleWriteRepository,
            RoleReadRepository roleReadRepository,
            AvatarWriteRepository avatarWriteRepository
    ) {
        return (args -> {
            // Role
            Role userRole = roleReadRepository.findByRole(RoleType.USER)
                    .orElseGet(() -> roleWriteRepository.save(new Role(RoleType.USER)));
            Role adminRole = roleReadRepository.findByRole(RoleType.ADMIN)
                    .orElseGet(() -> roleWriteRepository.save(new Role(RoleType.ADMIN)));
            em.clear();

            // User
            User admin = userReadRepository.findByUsername("admin")
                    .orElseGet(() -> {
                        Avatar avatar = avatarWriteRepository.save(newAvatar("admin"));
                        return userWriteRepository.save(newUser("admin", avatar, List.of(userRole, adminRole)));
                    });
            User test1 = userReadRepository.findByUsername("test1")
                    .orElseGet(() -> {
                        Avatar avatar = avatarWriteRepository.save(newAvatar("test1"));
                        return userWriteRepository.save(newUser("test1", avatar, List.of(userRole)));
                    });
            User test2 = userReadRepository.findByUsername("test2")
                    .orElseGet(() -> {
                        Avatar avatar = avatarWriteRepository.save(newAvatar("test2"));
                        return userWriteRepository.save(newUser("test2", avatar, List.of(userRole)));
                    });
            User test3 = userReadRepository.findByUsername("test3")
                    .orElseGet(() -> {
                        Avatar avatar = avatarWriteRepository.save(newAvatar("test3"));
                        return userWriteRepository.save(newUser("test3", avatar, List.of(userRole)));
                    });
            User test4 = userReadRepository.findByUsername("test4")
                    .orElseGet(() -> {
                        Avatar avatar = avatarWriteRepository.save(newAvatar("test4"));
                        return userWriteRepository.save(newUser("test4", avatar, List.of(userRole)));
                    });
            User test5 = userReadRepository.findByUsername("test5")
                    .orElseGet(() -> {
                        Avatar avatar = avatarWriteRepository.save(newAvatar("test5"));
                        return userWriteRepository.save(newUser("test5", avatar, List.of(userRole)));
                    });
            em.clear();
        });
    }

    @Bean
    CommandLineRunner dummyArticles() {
        return (args -> {
        });
    }
}
