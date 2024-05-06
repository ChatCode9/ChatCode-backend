package com.chatcode.global;

import com.chatcode.global.config.RestDocsConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.MockMvc;

@Import(RestDocsConfiguration.class)
@ExtendWith(RestDocumentationExtension.class)
public abstract class ControllerTest {

    @Autowired
    protected RestDocumentationResultHandler restDocs;

    @Autowired
    protected MockMvc mockMvc;

    //TODO Login Token관련 세팅
}
