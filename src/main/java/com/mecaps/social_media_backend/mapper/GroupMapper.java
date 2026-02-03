package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.request.GroupRequest;
import com.mecaps.social_media_backend.response.GroupResponse;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public Group toGroup(GroupRequest groupRequest, User user){
        return Group.builder()
                .name(groupRequest.getName())
                .description(groupRequest.getDescription())
                .groupType(groupRequest.getGroupType())
                .joinPolicy(groupRequest.getJoinPolicy())
                .postPolicy(groupRequest.getPostPolicy())
                .createdBy(user)
                .build();
    }

    public GroupResponse toResponse(Group group){
        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .groupType(group.getGroupType())
                .postPolicy(group.getPostPolicy())
                .joinPolicy(group.getJoinPolicy())
                .createdAt(group.getCreatedAt())
                .build();
    }
}
