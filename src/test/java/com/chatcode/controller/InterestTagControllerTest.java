package com.chatcode.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chatcode.domain.entity.InterestTag;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagIdRequest;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagNameRequest;
import com.chatcode.dto.tag.InterestTagRequest.InterestTagRenameRequest;
import com.chatcode.repository.tag.InterestTagReadRepository;
import com.chatcode.repository.tag.InterestTagWriteRepository;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@Sql("classpath:db/teardown.sql")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class InterestTagControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private InterestTagWriteRepository interestTagWriteRepository;
    @Autowired
    private InterestTagReadRepository interestTagReadRepository;
    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        InterestTag tag1 = interestTagWriteRepository.save(InterestTag.builder().name("tag1").build());
        InterestTag tag2 = interestTagWriteRepository.save(InterestTag.builder().name("tag2").build());
        InterestTag tag3 = interestTagWriteRepository.save(InterestTag.builder().name("tag3").build());
        em.clear();
    }

    @Test
    @DisplayName("모든 관심 태그를 조회한다.")
    void getAll() throws Exception {
        // when
        ResultActions resultActions = mvc.perform(get("/avatars/interest-tags"));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("관심 태그를 추가한다. (관리자 권한)")
    void add() throws Exception {
        // given
        List<InterestTagNameRequest> names = List.of(
                InterestTagNameRequest.builder().name("tag4").build(),
                InterestTagNameRequest.builder().name("tag5").build()
        );
        String requestBody = om.writeValueAsString(names);
        System.out.println("requestBody = " + requestBody);

        // when
        ResultActions resultActions = mvc.perform(post("/admin/interest-tags")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);

        // then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @DisplayName("관심 태그를 삭제한다. (관리자 권한)")
    void delete_test() throws Exception {
        // given
        Long deleteId = interestTagReadRepository.findByName("tag2").get().getId();
        InterestTagIdRequest request = InterestTagIdRequest.builder().id(deleteId).build();
        List<InterestTagIdRequest> requests = List.of(request);
        String requestBody = om.writeValueAsString(requests);

        // when
        ResultActions resultActions = mvc.perform(delete("/admin/interest-tags")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);

        // then
        resultActions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("관심 태그를 수정한다. (관리자 권한)")
    void update_test() throws Exception {
        // given
        Long updateId = interestTagReadRepository.findByName("tag3").get().getId();
        InterestTagRenameRequest request = InterestTagRenameRequest.builder()
                .id(updateId)
                .name("tag3_updated").build();
        List<InterestTagRenameRequest> requests = List.of(request);
        String requestBody = om.writeValueAsString(requests);

        // when
        ResultActions resultActions = mvc.perform(put("/admin/interest-tags")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("responseBody = " + responseBody);

        // then
        resultActions.andExpect(status().isOk());
    }
}