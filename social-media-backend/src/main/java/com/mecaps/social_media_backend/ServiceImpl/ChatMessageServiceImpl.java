package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.ChatMessage;
import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Mapper.ChatMessageMapper;
import com.mecaps.social_media_backend.Repository.ChatMessageRepository;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.ChatMessageRequest;
import com.mecaps.social_media_backend.Response.ChatMessageResponse;
import com.mecaps.social_media_backend.Service.ChatMessageService;
import com.mecaps.social_media_backend.Validations.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageServiceImpl implements ChatMessageService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final Validation validation;
    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Override
    public void sendMessage(ChatMessageRequest request) {
        User sender = validation.getUserById(request.getSenderId());
        User receiver = validation.getReceiverById(request.getReceiverId());
        ChatMessage chatMessage = chatMessageMapper.toChatMessage(request,sender,receiver);
        ChatMessage saved = chatMessageRepository.save(chatMessage);
        ChatMessageResponse response = chatMessageMapper.toChatMessageResponse(saved);

        String destination = "/queue/"+receiver.getId();
        // Send Message To Receiver Only
        log.info("sending chatmessage to destination: {}"+destination);
        simpMessagingTemplate.convertAndSend(
                destination,
                response
        );

    }
}
