package com.chatcode.domain.article;

import com.chatcode.domain.common.type.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum CategoryType implements BaseEnum<CategoryType> {
    QUESTION, //질문게시판
    FREE; //자유게시판

    @JsonCreator
    public static CategoryType fromName(String value) {
        return BaseEnum.getEnum(CategoryType.class, value);
    }
}
