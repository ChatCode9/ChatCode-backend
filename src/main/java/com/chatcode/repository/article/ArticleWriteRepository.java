package com.chatcode.repository.article;

import com.chatcode.domain.entity.Article;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleWriteRepository extends JpaRepository<Article, Long>, WriteRepository<Article> {
}
