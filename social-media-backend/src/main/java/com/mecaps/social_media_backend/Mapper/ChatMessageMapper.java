package com.mecaps.social_media_backend.Mapper;

import com.mecaps.social_media_backend.Entity.ChatMessage;
import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.ChatMessageRequest;
import com.mecaps.social_media_backend.Response.ChatMessageResponse;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ChatMessageMapper {

    public ChatMessage toChatMessage(ChatMessageRequest chatMessageRequest, User sender, User receiver) {

        return ChatMessage.builder()
                .content(chatMessageRequest.getContent())
                .sender(sender)
                .receiver(receiver)
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