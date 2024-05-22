package com.chatcode.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chatcode.domain.entity.Category;
import com.chatcode.dto.category.CategoryRequest.CategoryCreateRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateNameRequest;
import com.chatcode.dto.category.CategoryRequest.CategoryUpdateOrderRequest;
import com.chatcode.repository.category.CategoryReadRepository;
import com.chatcode.repository.category.CategoryWriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
class CategoryControllerTest {

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
    void getAll() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/categories"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void create() throws Exception {
        // given
        CategoryCreateRequest request = new CategoryCreateRequest();
        request.setName("Category_D");

        String requestBody = om.writeValueAsString(request);
        // when
        ResultActions resultActions = mvc.perform(
                post("/categories").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.name").value("Category_D"));
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void updateName() throws Exception {
        // given
        Category category = categoryReadRepository.findByName("Category_B").get();
        CategoryUpdateNameRequest request = new CategoryUpdateNameRequest();
        request.setName("Category_B_Updated");
        String requestBody = om.writeValueAsString(request);

        // when
        ResultActions resultActions = mvc.perform(
                put("/categories/" + category.getId() + "/name").content(requestBody).contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data.name").value("Category_B_Updated"));
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void updateOrders() throws Exception {
        // given
        Category categoryA = categoryReadRepository.findByName("Category_A").get();
        Category categoryB = categoryReadRepository.findByName("Category_B").get();
        Category categoryC = categoryReadRepository.findByName("Category_C").get();
        CategoryUpdateOrderRequest request = new CategoryUpdateOrderRequest();
        request.setOrders(List.of(categoryC.getId(), categoryA.getId(), categoryB.getId()));
        String requestBody = om.writeValueAsString(request);

        // when
        ResultActions resultActions = mvc.perform(
                put("/categories/order").content(requestBody).contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.data[0].name").value("Category_C"));
        resultActions.andExpect(jsonPath("$.data[1].name").value("Category_A"));
        resultActions.andExpect(jsonPath("$.data[2].name").value("Category_B"));
    }

    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    void delete_test() throws Exception {
        // given
        Category categoryB = categoryReadRepository.findByName("Category_B").get();

        // when
        ResultActions resultActions = mvc.perform(delete("/categories/" + categoryB.getId()));

        // then
        resultActions.andExpect(status().isOk());
        assertThat(categoryReadRepository.findByName("Category_B")).isEmpty();
    }

    private void dataSetting() {
        Category categoryA = categoryWriteRepository.save(Category.of("Category_A", 1));
        Category categoryB = categoryWriteRepository.save(Category.of("Category_B", 2));
        Category categoryC = categoryWriteRepository.save(Category.of("Category_C", 3));
        em.clear();
    }
}