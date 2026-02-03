package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.Enum.Role;
import com.mecaps.social_media_backend.Enum.Status;
import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.GroupMember;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.response.GroupMemberResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GroupMemberMapper {

    public GroupMember toGroupAdmin(Group group, User user){
        return GroupMember.builder()
                .group(group)
                .user(user)
                .role(Role.ADMIN)
                .status(Status.ACCEPTED)
                .build();
    }

    public GroupMember toGroupMember(Group group, User user){
        return GroupMember.builder()
                .group(group)
                .user(user)
                .role(Role.MEMBER)
                .status(Status.ACCEPTED)
                .joinedAt(LocalDateTime.now())
                .build();
    }

    public GroupMemberResponse toGroupMemberResponse(GroupMember groupMember){
        return GroupMemberResponse.builder()
                .memberId(groupMember.getId())
                .userId(groupMember.getId())
                .userName(groupMember.getUser().getUserName())
                .status(groupMember.getStatus())
                .role(groupMember.getRole())
                .joinedAt(groupMember.getJoinedAt())
                .build();
    }
}
