package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.ChatMessage;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.ChatMessageMapper;
import com.mecaps.social_media_backend.repository.ChatMessageRepository;

import com.mecaps.social_media_backend.request.ChatMessageRequest;
import com.mecaps.social_media_backend.response.ChatMessageResponse;
import com.mecaps.social_media_backend.service.ChatMessageService;
import com.mecaps.social_media_backend.validations.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final Validation validation;

    @Override
    public void sendMessage(ChatMessageRequest chatMessageRequest ){

        // fetch sender
        User sender = validation.getUserById(chatMessageRequest.getSenderId());

        User receiver = validation.getReceiverById(chatMessageRequest.getReceiverId());

        ChatMessage chatMessage = chatMessageMapper.ToChatMessage(chatMessageRequest,sender,receiver);
        chatMessageRepository.save(chatMessage);


        ChatMessageResponse response = chatMessageMapper.toChatMessageResponse(chatMessage);


      String destination ="/queue/"+receiver.getId();
      log.info("sending chatmessage to destination : {}"+destination);
      messagingTemplate.convertAndSend(
              destination,
              response
      );
    }


}
