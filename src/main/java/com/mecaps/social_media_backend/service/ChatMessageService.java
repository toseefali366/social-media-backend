package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.entity.ChatMessage;
import com.mecaps.social_media_backend.request.ChatMessageRequest;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;

public interface ChatMessageService {

    void sendMessage(ChatMessageRequest chatMessageRequest
            );
}
