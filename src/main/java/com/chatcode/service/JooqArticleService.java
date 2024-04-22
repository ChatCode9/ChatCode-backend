package com.chatcode.repository;

import com.chatcode.jooq.tables.Article;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

@Service
public class JooqArticleRepository {

    private final DSLContext dsl;

    public JooqArticleRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public List<com.chatcode.jooq.tables.pojos.Article> findAll() {
        return dsl.selectFrom(Article).fetchInto(com.chatcode.jooq.tables.pojos.Article.class);
    }
}
