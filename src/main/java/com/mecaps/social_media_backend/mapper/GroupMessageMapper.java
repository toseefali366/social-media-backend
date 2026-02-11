package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.Enum.Status;
import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.GroupMessage;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.response.GroupMessageResponse;
import org.springframework.stereotype.Component;

@Component
public class GroupMessageMapper {

     public GroupMessage toGroupMessage(Group group , User user, String message){

         return GroupMessage.builder()
                 .group(group)
                 .sender(user)
                 .message(message)
                 .status(Status.ACCEPTED)
                 .build();
     }

    public GroupMessageResponse toGroupMessageResponse(GroupMessage groupMessage) {
        return GroupMessageResponse.builder()
                .messageId(groupMessage.getId())
                .groupId(groupMessage.getGroup().getId())
                .senderId(groupMessage.getSender().getId())
                .senderName(groupMessage.getSender().getUserName())
                .message(groupMessage.getMessage())
                .sentAt(groupMessage.getSentAt())
                .build();
    }
}
