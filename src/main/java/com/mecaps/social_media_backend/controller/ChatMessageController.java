package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.ChatMessageMapper;
import com.mecaps.social_media_backend.repository.ChatMessageRepository;
import com.mecaps.social_media_backend.repository.UserRepository;
import com.mecaps.social_media_backend.request.ChatMessageRequest;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.ChatMessageService;
import com.mecaps.social_media_backend.validations.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatMessageController {
   private final ChatMessageService chatMessageService;



    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload ChatMessageRequest chatMessageRequest) {
        if(chatMessageRequest.getReceiverId()==null){
            log.error("receiver ID is null");
            throw new IllegalArgumentException("receiver Id is null");
        }
        if(chatMessageRequest.getSenderId()==null){
            log.error("sender ID is null");
            throw new IllegalArgumentException("sender ID is null");
        }
        chatMessageService.sendMessage(chatMessageRequest);

    }
}
