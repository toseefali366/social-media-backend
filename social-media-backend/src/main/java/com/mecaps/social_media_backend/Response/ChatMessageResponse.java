package com.mecaps.social_media_backend.Response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ChatMessageResponse {

    private Long messageId;
    private String content;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime createdAt;
}