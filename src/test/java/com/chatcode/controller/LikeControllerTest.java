package com.chatcode.controller;

import com.chatcode.domain.LikeableContentType;
import com.chatcode.dto.like.LikeRequest;
import com.chatcode.exception.reaction.AlreadyReactException;
import com.chatcode.global.ControllerTest;
import com.chatcode.service.LikeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class LikeControllerTest extends ControllerTest {

    @Mock
    private LikeService likeService;

    @InjectMocks
    private LikeController likeController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(likeController).build();
        objectMapper = new ObjectMapper();
    }

    @DisplayName("게시글에_대해_좋아요를_요청하면_200을_응답한다")
    @Test
    public void 게시글에_대해_좋아요를_요청하면_200을_응답한다() throws Exception {
        // Given
        int articleId = 1;
        LikeRequest likeRequest = new LikeRequest(true);
        willDoNothing().given(likeService).like(LikeableContentType.ARTICLE, articleId, 1, likeRequest);

        // When & Then
        mockMvc.perform(post("/articles/{articleId}/like", articleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"));

        then(likeService).should().like(LikeableContentType.ARTICLE, articleId, 1, likeRequest);
    }

    @Test
    public void 해당게시글에_이미_반응을_한경우_400을_반환한다() throws Exception {
        // Given
        int articleId = 1;
        LikeRequest likeRequest = new LikeRequest(true);
        willThrow(AlreadyReactException.class).given(likeService).like(LikeableContentType.ARTICLE, articleId, 1, likeRequest);

        // When & Then
        mockMvc.perform(post("/articles/{articleId}/like", articleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("Already reacted to this article"));

        // Verify
        then(likeService).should().like(LikeableContentType.ARTICLE, articleId, 1, likeRequest);
    }

    @Test
    public void 댓글에_대해_좋아요를_요청하면_200을_응답한다() throws Exception {
        // Given
        int opinionId = 1;
        LikeRequest likeRequest = new LikeRequest(true);
        willDoNothing().given(likeService).like(LikeableContentType.OPINION, opinionId, 1, likeRequest);

        // When & Then
        mockMvc.perform(post("/opinions/{opinionId}/like", opinionId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("success"));

        then(likeService).should().like(LikeableContentType.OPINION, opinionId, 1, likeRequest);
    }
}
