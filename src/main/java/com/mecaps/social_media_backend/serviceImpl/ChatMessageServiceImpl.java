package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.ChatMessage;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.ChatMessageMapper;
import com.mecaps.social_media_backend.repository.ChatMessageRepository;
import com.mecaps.social_media_backend.repository.UserRepository;
import com.mecaps.social_media_backend.request.ChatMessageRequest;
import com.mecaps.social_media_backend.response.ChatMessageResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
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

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final UserRepository userRepository;
    private final Validation validation;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public void sendMessage(ChatMessageRequest chatMessageRequest
            ) {

        User sender = validation.getUserById(chatMessageRequest.getSenderId());
        User receiver = validation.getReceiverById(chatMessageRequest.getReceiverId());
        ChatMessage chatMessage =chatMessageMapper.toChatMessage(chatMessageRequest, sender, receiver);
        ChatMessage saved = chatMessageRepository.save(chatMessage);
        ChatMessageResponse response =chatMessageMapper.toChatMessageResponse(saved);

        String destination = "/queue/"+receiver.getId();
        // Send Message To Receiver Only
        log.info("sending chatmessage to destination: {}"+destination);
        simpMessagingTemplate.convertAndSend(
                destination,
                response
        );
    }
}
