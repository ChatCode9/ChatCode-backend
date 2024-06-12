package com.chatcode.domain.article;

import com.chatcode.domain.common.type.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
@Getter
public enum SortType implements BaseEnum<SortType> {
    LATEST("dateCreated", Sort.Direction.DESC),
    EARLIEST("dateCreated", Sort.Direction.ASC),
    POPULARITY("likeCount", Sort.Direction.DESC);

    private final String field;
    private final Sort.Direction direction;

    @JsonCreator
    public static SortType fromName(String value) {
        return BaseEnum.getEnum(SortType.class, value);
    }
}
