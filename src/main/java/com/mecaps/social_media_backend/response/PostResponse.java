package com.mecaps.social_media_backend.response;

import com.mecaps.social_media_backend.Enum.PostVisibility;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String text;
    private PostVisibility postVisibility;
    private LocalDateTime createdAt;
    private UserSummaryResponse user;
    private List<PostContentResponse> content;

}
