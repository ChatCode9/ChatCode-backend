package com.chatcode.config.dummy;

import com.chatcode.domain.RoleType;
import com.chatcode.domain.entity.*;
import com.chatcode.repository.article.ArticleWriteRepository;
import com.chatcode.repository.avatar.AvatarReadRepository;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import com.chatcode.repository.category.CategoryReadRepository;
import com.chatcode.repository.category.CategoryWriteRepository;
import com.chatcode.repository.content.ContentWriteRepository;
import com.chatcode.repository.role.RoleReadRepository;
import com.chatcode.repository.role.RoleWriteRepository;
import com.chatcode.repository.tag.InterestTagReadRepository;
import com.chatcode.repository.tag.InterestTagWriteRepository;
import com.chatcode.repository.user.UserReadRepository;
import com.chatcode.repository.user.UserWriteRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class DummyDevInit extends DummyObject {

    private final EntityManager em;

    private static final Faker faker = new Faker();
    private static int categorySortOrder = 1;

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
    CommandLineRunner dummyCategories(CategoryWriteRepository categoryWriteRepository,
                                      CategoryReadRepository categoryReadRepository) {
        return (args -> {
            List<Category> categories = List.of(
                    newCategory("Free"),
                    newCategory("Question")
            );
            categories.forEach(category -> {
                categoryReadRepository.findByName(category.getName())
                        .orElseGet(() -> categoryWriteRepository.save(category));
            });
            em.clear();
        });
    }


    private Category newCategory(String community) {
        return new Category().builder()
                .name(community)
                .sortOrder(categorySortOrder++)
                .build();
    }


    @Bean
    CommandLineRunner dummyArticles(ArticleWriteRepository articleWriteRepository,
                            AvatarWriteRepository avatarWriteRepository,
                            ContentWriteRepository contentWriteRepository, AvatarReadRepository avatarReadRepository) {

        String newAvatarName = faker.name().name();
        avatarWriteRepository.save(newAvatar(newAvatarName));

        Avatar newAvatar = avatarReadRepository.findByName(newAvatarName);

        List<Content> contents = List.of(newContent(faker.text().text(100)),
                newContent(faker.text().text(100)),
                newContent(faker.text().text(100)));

        contents.forEach(content -> contentWriteRepository.save(content));

        List<Article> articles = contents.stream().map(
                content ->
                        newArticle(newAvatar.getId(), content.getId())
        ).toList();

        return (args -> {
            articles.forEach(article ->
                    articleWriteRepository.save(article)
            );
            em.clear();
        });
    }

    public Article newArticle(Long authorId, Long contentId) {
        return Article.builder()
                .version(0L)
                .authorId(authorId)
                .completed(faker.bool().bool())
                .contentId(contentId)
                .createIp(faker.internet().ipV4Address())
                .enabled(faker.bool().bool())
                .lastEditorId(authorId)
                .noteCount(faker.random().nextInt())
                .scrapCount(faker.random().nextInt())
                .selectedNoteId(null)
                .tagString(faker.expression("#{regexify '(a|b){2,3}'}"))
                .title(faker.book().title())
                .viewCount(faker.random().nextInt())
                .likeCount(faker.random().nextInt())
                .dislikeCount(faker.random().nextInt())
                .categoryId(faker.random().nextLong(1, 2))
                .build();
    }

}

