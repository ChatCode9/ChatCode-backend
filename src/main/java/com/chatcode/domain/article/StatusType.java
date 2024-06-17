package com.chatcode.domain.article;

import com.chatcode.domain.common.type.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusType implements BaseEnum<StatusType> {
    WAIT(false), //해결대기
    FINISH(true); //해결완료

    private final boolean value;

    public static StatusType of(String value) {
        return BaseEnum.getEnum(StatusType.class, value);
    }

    public static StatusType of(boolean value) {
        for (StatusType status : values()) {
            if (status.isValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }
}
