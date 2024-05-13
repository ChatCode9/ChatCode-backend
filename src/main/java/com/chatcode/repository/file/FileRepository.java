package com.chatcode.repository.file;

import com.chatcode.domain.entity.File;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long>, WriteRepository<File> {
}
