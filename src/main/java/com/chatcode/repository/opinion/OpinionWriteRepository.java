package com.chatcode.repository.opinion;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Opinion;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionWriteRepository extends JpaRepository<Opinion, Long>, WriteRepository<Opinion> {
}
