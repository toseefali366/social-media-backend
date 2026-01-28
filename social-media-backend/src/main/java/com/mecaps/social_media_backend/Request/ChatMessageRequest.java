package com.mecaps.social_media_backend.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageRequest {
    private Long senderId;
    private Long receiverId;
    private String content;
    private LocalDateTime timestamp;

}

