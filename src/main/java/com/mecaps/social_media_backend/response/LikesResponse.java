package com.mecaps.social_media_backend.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor

public class LikesResponse {

    private Long id;
    private Long postId;
    private Long userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private LocalDateTime likedAt;

}
