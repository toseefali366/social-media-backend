package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.request.ChatMessageRequest;
import org.springframework.stereotype.Service;

public interface ChatMessageService {

     void sendMessage(ChatMessageRequest chatMessageRequest );
}
