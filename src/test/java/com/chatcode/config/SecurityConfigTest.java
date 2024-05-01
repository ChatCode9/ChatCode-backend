package com.chatcode.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class SecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void authentication_test() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(get("/admin/hello"));
        int httpStatusCode = resultActions.andReturn().getResponse().getStatus();

        // then
        Assertions.assertThat(httpStatusCode).isEqualTo(HttpStatus.UNAUTHORIZED.value()); // redirect
    }

}