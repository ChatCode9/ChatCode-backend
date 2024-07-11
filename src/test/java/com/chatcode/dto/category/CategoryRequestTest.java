package com.chatcode.dto.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.chatcode.domain.entity.Category;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateOrderRequest;
import com.chatcode.repository.category.CategoryReadRepository;
import com.chatcode.repository.category.CategoryWriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class CategoryRequestTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private CategoryWriteRepository categoryWriteRepository;
    @Autowired
    private CategoryReadRepository categoryReadRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        dataSetting();
    }

    @Test
    @DisplayName("중복되는 카테고리 아이디로 순서 수정 요청 시 예외 발생")
    @WithMockUser(roles = "ADMIN")
    void updateOrderWithDuplicateCategoryId() throws Exception {
        // given
        CategoryUpdateOrderRequest request = new CategoryUpdateOrderRequest(List.of(3L, 3L, 1L));
        String requestBody = om.writeValueAsString(request);

        // when
        ResultActions resultActions = mvc.perform(
                put("/categories/order").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);

        // then
        // get 400 error
        resultActions.andExpect(result -> assertEquals(400, result.getResponse().getStatus()));
    }

    private void dataSetting() {
        Category categoryA = categoryWriteRepository.save(Category.of("Category_A", 1));
        Category categoryB = categoryWriteRepository.save(Category.of("Category_B", 2));
        Category categoryC = categoryWriteRepository.save(Category.of("Category_C", 3));
        em.clear();
    }
}