package com.mecaps.social_media_backend.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMessageResponse {

  private Long messageId;
  private Long groupId;
  private Long senderId;
  private String senderName;
  private LocalDateTime sentAt;
  private String message;



}


