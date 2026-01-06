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
    private Long userId;
    private String text;
    private String firstName;
    private String lastName;
    private String profilePicture;

    private LocalDateTime createdAt;
}
