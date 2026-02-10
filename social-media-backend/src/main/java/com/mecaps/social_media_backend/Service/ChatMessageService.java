package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Request.ChatMessageRequest;

public interface ChatMessageService {
    public void sendMessage(ChatMessageRequest request);
}
