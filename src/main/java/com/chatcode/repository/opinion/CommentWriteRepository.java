package com.chatcode.repository.opinion;

import com.chatcode.domain.entity.Comment;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionWriteRepository extends JpaRepository<Comment, Long>, WriteRepository<Comment> {
}
