package com.chatcode.domain.article;

import com.chatcode.domain.common.type.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum StatusType implements BaseEnum<StatusType> {
    WAIT, //해결대기
    FINISH; //해결완료

    @JsonCreator
    public static StatusType create(String value) {
        return Enum.valueOf(StatusType.class, value);
    }
}
