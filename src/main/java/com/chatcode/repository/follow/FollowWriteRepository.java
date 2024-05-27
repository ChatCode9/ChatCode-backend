package com.chatcode.repository.follow;

import com.chatcode.domain.entity.Follow;
import com.chatcode.repository.WriteRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowWriteRepository extends JpaRepository<Follow, Follow.PK>, WriteRepository<Follow> {
}
