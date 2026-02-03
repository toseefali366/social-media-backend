package com.mecaps.social_media_backend.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
private Long senderId;
    private Long receiverId;
    private String content;
}
