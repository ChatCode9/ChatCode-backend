package com.chatcode.repository.article;

import com.chatcode.domain.entity.ArticleTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {
    List<ArticleTag> findByArticleId(Long articleId);
}
