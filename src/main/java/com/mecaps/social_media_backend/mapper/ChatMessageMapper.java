package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.ChatMessage;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.request.ChatMessageRequest;
import com.mecaps.social_media_backend.response.ChatMessageResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChatMessageMapper {

    public ChatMessage toChatMessage(ChatMessageRequest chatMessageRequest, User sender, User recever) {

        return ChatMessage.builder()
                .content(chatMessageRequest.getContent())
                .sender(sender)
                .receiver(recever)
                .createdAt(LocalDateTime.now())
                .build();
    }
    public ChatMessageResponse toChatMessageResponse(ChatMessage message) {

        return ChatMessageResponse.builder()
                .messageId(message.getId())
                .content(message.getContent())
                .senderId(message.getSender().getId())
                .receiverId(message.getReceiver().getId())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
