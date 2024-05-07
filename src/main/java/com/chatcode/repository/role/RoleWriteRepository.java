package com.chatcode.repository.role;

import com.chatcode.domain.entity.Role;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleWriteRepository extends JpaRepository<Role, Long>, WriteRepository<Role> {
}
