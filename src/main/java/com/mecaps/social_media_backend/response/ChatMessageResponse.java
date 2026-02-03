package com.mecaps.social_media_backend.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ChatMessageResponse {
    private Long messageId;
    private String content;
    private Long senderId;
    private Long receiverId;
    private LocalDateTime createdAt;
}
