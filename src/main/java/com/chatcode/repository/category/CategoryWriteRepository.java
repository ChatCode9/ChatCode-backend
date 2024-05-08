package com.chatcode.repository.category;

import com.chatcode.domain.entity.Category;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryWriteRepository extends JpaRepository<Category, String>, WriteRepository<Category> {
}
