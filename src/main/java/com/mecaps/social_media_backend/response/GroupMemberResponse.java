package com.mecaps.social_media_backend.response;

import com.mecaps.social_media_backend.Enum.Role;
import com.mecaps.social_media_backend.Enum.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMemberResponse {

    private Long memberId;
    private Long userId;
    private String userName;
    private Role role;
    private Status status;
    private LocalDateTime joinedAt;

}
