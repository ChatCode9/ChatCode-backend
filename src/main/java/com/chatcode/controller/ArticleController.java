package com.chatcode.controller;

import com.chatcode.jooq.tables.pojos.Article;
import com.chatcode.service.JooqArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private JooqArticleService service;

    @GetMapping("")
    public ResponseEntity<List<Article>> findAll(){
        List<Article> articles = service.findAll();

        return ResponseEntity.ok(articles);
    }
}
