package com.mecaps.social_media_backend.response;

import com.mecaps.social_media_backend.Enum.GroupType;
import com.mecaps.social_media_backend.Enum.JoinPolicy;
import com.mecaps.social_media_backend.Enum.PostPolicy;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class GroupResponse {
    private Long id;
    private String name;
    private String description;
    private GroupType groupType;
    private JoinPolicy joinPolicy;
    private PostPolicy postPolicy;
    private LocalDateTime createdAt;
}
