package com.chatcode.repository.avatar;

import com.chatcode.domain.entity.Avatar;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarWriteRepository extends JpaRepository<Avatar, Long>, WriteRepository<Avatar> {
}
