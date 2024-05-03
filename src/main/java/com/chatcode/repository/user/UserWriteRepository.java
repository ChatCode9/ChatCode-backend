package com.chatcode.repository.user;

import com.chatcode.domain.entity.User;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWriteRepository extends JpaRepository<User, Long>, WriteRepository<User> {
}
