package com.chatcode.repository.scrap;

import com.chatcode.domain.entity.Scrap;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapWriteRepository extends JpaRepository<Scrap, Long>, WriteRepository<Scrap> {
}