package com.chatcode.domain.article;

import com.chatcode.domain.common.type.BaseEnum;

public enum CategoryType implements BaseEnum<CategoryType> {
    QUESTION, //질문게시판
    FREE; //자유게시판

    public static CategoryType of(String value) {
        return BaseEnum.getEnum(CategoryType.class, value);
    }
}
