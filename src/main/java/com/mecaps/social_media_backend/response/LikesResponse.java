package com.mecaps.social_media_backend.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesResponse {
    private Long id;
    private Long postId;
    private UserSummaryResponse user;
    private LocalDateTime likedAt;
}