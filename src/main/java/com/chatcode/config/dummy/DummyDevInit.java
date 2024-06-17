package com.chatcode.config.dummy;

import com.chatcode.domain.RoleType;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.InterestTag;
import com.chatcode.domain.entity.Role;
import com.chatcode.domain.entity.User;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import com.chatcode.repository.role.RoleReadRepository;
import com.chatcode.repository.role.RoleWriteRepository;
import com.chatcode.repository.tag.InterestTagReadRepository;
import com.chatcode.repository.tag.InterestTagWriteRepository;
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

            List<User> users = List.of(
                    newUser("admin", newAvatar("admin"), List.of(userRole, adminRole)),
                    newUser("test1", newAvatar("test1"), List.of(userRole)),
                    newUser("test2", newAvatar("test2"), List.of(userRole)),
                    newUser("test3", newAvatar("test3"), List.of(userRole)),
                    newUser("test4", newAvatar("test4"), List.of(userRole)),
                    newUser("test5", newAvatar("test5"), List.of(userRole)));

            users.forEach(user -> {
                userReadRepository.findByUsername(user.getUsername())
                        .orElseGet(() -> {
                            Avatar avatar = avatarWriteRepository.save(user.getAvatar());
                            return userWriteRepository.save(newUser(user.getUsername(), avatar, user.getRoles()));
                        });
            });
            em.clear();
        });
    }

    @Bean
    CommandLineRunner dummyInterestTags(InterestTagReadRepository interestTagReadRepository,
                                        InterestTagWriteRepository interestTagWriteRepository) {
        return (args -> {
            List<InterestTag> interestTags = List.of(
                    newInterestTag("frontend"),
                    newInterestTag("backend"),
                    newInterestTag("embeded"),
                    newInterestTag("ui & ux"),
                    newInterestTag("design"),
                    newInterestTag("web"),
                    newInterestTag("ios"),
                    newInterestTag("mobile"),
                    newInterestTag("ai"),
                    newInterestTag("game"),
                    newInterestTag("devops"),
                    newInterestTag("deep learning"),
                    newInterestTag("data"),
                    newInterestTag("desktop"),
                    newInterestTag("algorithm"),
                    newInterestTag("native"),
                    newInterestTag("app"),
                    newInterestTag("protect"),
                    newInterestTag("study"),
                    newInterestTag("beginner"),
                    newInterestTag("job"),
                    newInterestTag("hire"),
                    newInterestTag("employment"),
                    newInterestTag("conference"),
                    newInterestTag("job fair"),
                    newInterestTag("competition"),
                    newInterestTag("hackathon")
            );
            interestTags.forEach(tag -> {
                interestTagReadRepository.findByName(tag.getName())
                        .orElseGet(() -> interestTagWriteRepository.save(tag));
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

