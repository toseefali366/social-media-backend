package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
}
