package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.ChatMessage;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.request.ChatMessageRequest;
import com.mecaps.social_media_backend.response.ChatMessageResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class ChatMessageMapper {

    public ChatMessage ToChatMessage(ChatMessageRequest chatMessageRequest, User sender,User receiver){

        return ChatMessage.builder()
                .content(chatMessageRequest.getContent())
                .sender(sender)
                .receiver(receiver)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ChatMessageResponse toChatMessageResponse(ChatMessage message){
        return ChatMessageResponse.builder()
                .messageId(message.getId())
                .content(message.getContent())
                .receiverId(message.getReceiver().getId())
                .senderId(message.getSender().getId())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
