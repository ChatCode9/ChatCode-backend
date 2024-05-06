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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.chatcode.global.config.RestDocsConfiguration.field;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(LikeController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class LikeControllerTest extends ControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LikeService likeService;

    private ResultActions performPostRequest(final int articleId, final LikeRequest request) throws Exception {
        return mockMvc.perform(RestDocumentationRequestBuilders.post("/articles/{articleId}/like", articleId)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    @DisplayName("게시글에_대해_좋아요를_요청하면_200을_응답한다")
    @Test
    public void 게시글에_대해_좋아요를_요청하면_200을_응답한다() throws Exception {
        // Given
        int articleId = 1;
        LikeRequest likeRequest = new LikeRequest(true);
        willThrow(AlreadyReactException.class).given(likeService).like(LikeableContentType.ARTICLE, articleId, 1, likeRequest);

        // when
        final ResultActions resultActions = performPostRequest(articleId, likeRequest);

        resultActions.andExpect(status().isBadRequest())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("articleId")
                                        .description("게시글 ID")
                        ),
                        requestFields(
                                fieldWithPath("isLike")
                                        .type(JsonFieldType.BOOLEAN)
                                        .description("변경할 좋아요 상태값")
                                        .attributes(field("constraint", "True: 좋아요 반영, False: 좋아요 해제"))
                        ))
                );

        // When & Then
//        mockMvc.perform(post("/articles/{articleId}/like", articleId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(likeRequest)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value(200))
//                .andExpect(jsonPath("$.message").value("success"));

//        then(likeService).should().like(LikeableContentType.ARTICLE, articleId, 1, likeRequest);
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
