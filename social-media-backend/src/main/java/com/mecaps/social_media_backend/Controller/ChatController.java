package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Entity.ChatMessage;
import com.mecaps.social_media_backend.Request.ChatMessageRequest;
import com.mecaps.social_media_backend.Service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatMessageService chatMessageService;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload ChatMessageRequest request) {

      if (request.getReceiverId()==null){
          throw new IllegalArgumentException("receiverId is null");
      }
if(request.getSenderId()==null){
    throw new IllegalArgumentException("SenderId is null");
}
chatMessageService.sendMessage(request);

    }
}
