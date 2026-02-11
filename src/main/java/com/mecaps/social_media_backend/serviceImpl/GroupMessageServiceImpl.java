package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.GroupMessage;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.GroupMessageMapper;
import com.mecaps.social_media_backend.repository.GroupMemberRepository;
import com.mecaps.social_media_backend.repository.GroupMessageRepository;
import com.mecaps.social_media_backend.request.GroupMessageRequest;
import com.mecaps.social_media_backend.response.GroupMessageResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.GroupMessageService;
import com.mecaps.social_media_backend.validations.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupMessageServiceImpl implements GroupMessageService {

    private final GroupMessageRepository groupMessageRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupMessageMapper groupMessageMapper;
    private final Validation validation;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendGroupMessage(Long groupId, GroupMessageRequest groupMessageRequest,
                                 User currentUser) {


        // Fetching Group with groupId
        Group group = validation.getGroupById(groupId);

       User currentUserId = validation.getUserById(currentUser.getId());

        // Checking the user is groupMember or not
        Boolean isMember = groupMemberRepository.existsByGroupAndUser(group, currentUserId);

        if (!isMember) {
            throw new RuntimeException("You are not allowed to send message");
        }

        // Creating Message
        GroupMessage message = groupMessageMapper.toGroupMessage(group, currentUserId
                , groupMessageRequest.getMessage());

        // Save Message
        GroupMessage saved = groupMessageRepository.save(message);

        // Return Response
       GroupMessageResponse response =  groupMessageMapper.toGroupMessageResponse(saved);


       String destination = "/topic/group/" + group.getId();
       log.info("Sending message to destination: {}", destination);
        messagingTemplate.convertAndSend
                (destination, response);

                  // return response;
    }
    @Override
    public List<GroupMessageResponse> getGroupMessages(Long groupId) {
        Group group = validation.getGroupById(groupId); // fetch group or throw
        List<GroupMessage> messages = groupMessageRepository.findByGroupIdOrderBySentAtAsc(groupId);
        return messages.stream()
                .map(groupMessageMapper::toGroupMessageResponse)
                .toList();
    }

}
