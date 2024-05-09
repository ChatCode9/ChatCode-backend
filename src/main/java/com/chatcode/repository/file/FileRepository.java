package com.chatcode.repository.file;

import com.chatcode.domain.entity.FileEntity;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long>, WriteRepository<FileEntity> {
}
