package com.mecaps.social_media_backend.response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private Long postId;
    private String text;
    private LocalDateTime createdAt;
    private UserSummaryResponse user;
}
