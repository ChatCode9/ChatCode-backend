package com.chatcode.repository.category;

import static org.assertj.core.api.Assertions.assertThat;

import com.chatcode.domain.entity.Category;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
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
class CategoryReadRepositoryTest {

    @Autowired
    private CategoryReadRepository categoryReadRepository;
    @Autowired
    private CategoryWriteRepository categoryWriteRepository;

    @BeforeEach
    void setUp() {
        categoryWriteRepository.save(Category.of("Category A", 1));
        categoryWriteRepository.save(Category.of("Category B", 2));
        categoryWriteRepository.save(Category.of("Category C", 3));
    }

    @AfterEach
    void tearDown() {
        categoryWriteRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<Category> all = categoryReadRepository.findAll();
        assertThat(all.size()).isEqualTo(3);
        assertThat(all.get(0).getName()).isEqualTo("Category A");
        assertThat(all.get(2).getName()).isEqualTo("Category C");
    }

    @Test
    void count() {
        Integer count = categoryReadRepository.count();
        assertThat(count).isEqualTo(3);
    }
}