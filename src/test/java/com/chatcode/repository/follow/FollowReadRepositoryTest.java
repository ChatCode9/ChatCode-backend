package com.chatcode.repository.follow;

import static org.junit.jupiter.api.Assertions.*;

import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Follow;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;

@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class FollowReadRepositoryTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FollowReadRepository followReadRepository;

    @Autowired
    private AvatarWriteRepository avatarWriteRepository;

    @Autowired
    private FollowWriteRepository followWriteRepository;

    @BeforeEach
    @Transactional
    void setUp() {
        // Insert Avatars
        Avatar avatar1 = avatarWriteRepository.save(Avatar.builder()
                .activityPoint(0)
                .nickname("Avatar1")
                .picture("picture1.jpg")
                .build());
        Avatar avatar2 = avatarWriteRepository.save(Avatar.builder()
                .activityPoint(0)
                .nickname("Avatar2")
                .picture("picture2.jpg")
                .build());
        Avatar avatar3 = avatarWriteRepository.save(Avatar.builder()
                .activityPoint(0)
                .nickname("Avatar3")
                .picture("picture3.jpg")
                .build());

        // Insert Follows
        followWriteRepository.save(Follow.of(avatar1, avatar2));
        followWriteRepository.save(Follow.of(avatar1, avatar3));
        followWriteRepository.save(Follow.of(avatar2, avatar1));
        em.clear();
    }

    @Test
    void testCountByFollower() {
        Long count = followReadRepository.countByFollower(2L);
        assertEquals(1L, count);
    }

    @Test
    void testCountByFollowing() {
        Long count = followReadRepository.countByFollowing(1L);
        assertEquals(2L, count);
    }

    @Test
    void testFindFollowersProfiles() {
        List<Avatar> avatars = followReadRepository.findFollowersProfiles(2L);
        assertNotNull(avatars);
        assertEquals(1, avatars.size());
        assertEquals(1L, avatars.get(0).getId());
        assertEquals("Avatar1", avatars.get(0).getNickname());
        assertEquals("picture1.jpg", avatars.get(0).getPicture());
    }

    @Test
    void testFindFollowingsProfiles() {
        List<Avatar> avatars = followReadRepository.findFollowingsProfiles(1L);
        assertNotNull(avatars);
        assertEquals(2, avatars.size());
        assertTrue(avatars.stream().anyMatch(avatar -> avatar.getId().equals(2L)));
        assertTrue(avatars.stream().anyMatch(avatar -> avatar.getId().equals(3L)));
    }
}