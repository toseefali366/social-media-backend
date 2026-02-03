package com.mecaps.social_media_backend.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class GroupMessageResponse {
    private Long messageId;
    private Long groupId;
    private Long senderId;
    private String senderName;
    private String message;
    private LocalDateTime sentAt;
}
