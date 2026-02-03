package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.entity.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMessageRepository extends JpaRepository<GroupMessage,Long> {
List<GroupMessage> findByGroupIdOrderBySentAtAsc(Long groupId);

}
