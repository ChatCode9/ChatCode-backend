package com.chatcode.domain.article;

import com.chatcode.domain.common.type.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jooq.SortField;

import java.time.LocalDateTime;

import static com.chatcode.jooq.tables.Article.ARTICLE;

@AllArgsConstructor
@Getter
public enum SortType implements BaseEnum<SortType> {
    LATEST("dateCreated", ARTICLE.DATE_CREATED.desc()),
    EARLIEST("dateCreated", ARTICLE.DATE_CREATED.asc());

    private final String field;
    private final SortField<LocalDateTime> direction;

    @JsonCreator
    public static SortType of(String value) {
        return BaseEnum.getEnum(SortType.class, value);
    }
}
