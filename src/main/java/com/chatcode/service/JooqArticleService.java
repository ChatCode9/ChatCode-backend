package com.chatcode.service;

import static com.chatcode.jooq.tables.Article.ARTICLE;

import com.chatcode.jooq.tables.pojos.Article;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JooqArticleService {

    @Autowired
    private DSLContext dsl;

    public List<Article> findAll() {
        return dsl.selectFrom(ARTICLE).fetchInto(Article.class);
    }
}
