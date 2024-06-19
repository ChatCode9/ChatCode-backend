package com.chatcode.repository.content;

import com.chatcode.domain.entity.Content;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentWriteRepository extends JpaRepository<Content, Long>, WriteRepository<Content> {
}
