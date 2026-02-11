package com.mecaps.social_media_backend.response;

import com.mecaps.social_media_backend.Enum.GroupType;
import com.mecaps.social_media_backend.Enum.JoinPolicy;
import com.mecaps.social_media_backend.Enum.PostPolicy;
import lombok.*;
import org.aspectj.lang.JoinPoint;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GroupResponse {
    private Long id;
    private String name;
    private String description;
    private GroupType groupType;
    private PostPolicy postPolicy;
    private JoinPolicy joinPolicy;
    private LocalDateTime createdAt;

}

