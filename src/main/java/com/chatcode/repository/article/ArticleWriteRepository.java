package com.chatcode.repository.article;

import com.chatcode.domain.entity.Article;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleWriteRepository extends JpaRepository<Article, Long>, WriteRepository<Article> {
    List<Article> findAllBy(final Pageable pageable);
}
