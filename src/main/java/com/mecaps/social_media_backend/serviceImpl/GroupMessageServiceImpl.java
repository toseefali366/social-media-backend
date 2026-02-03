package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.GroupMessage;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.GroupMessageMapper;
import com.mecaps.social_media_backend.repository.GroupMemberRepository;
import com.mecaps.social_media_backend.repository.GroupMessageRepository;
import com.mecaps.social_media_backend.request.GroupMessageRequest;
import com.mecaps.social_media_backend.response.GroupMessageResponse;
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
    private final GroupMessageMapper groupMessageMapper;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupMessageRepository groupMessageRepository;
    private final Validation validation;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendGroupMessage(Long groupId, GroupMessageRequest
            groupMessageRequest, User currentUser) {

        // fetching group with group ID
        Group group = validation.getGroupById(groupId);

        // fetching current user
        User currentUserId = validation.getUserById(currentUser.getId());

        // checking user is a Group member or not
        Boolean isMember = groupMemberRepository.existsByGroupAndUser(group, currentUserId);

        if (!isMember) {
            throw new RuntimeException("You are not a member of this group");
        }

        // creating message
        GroupMessage message = groupMessageMapper.toGroupMessage(group, currentUserId, groupMessageRequest.getMessage());
        // saving message
        GroupMessage saved = groupMessageRepository.save(message);

        // returning reponse
        GroupMessageResponse response = groupMessageMapper.toGroupMessageResponse(saved);

        String destination = "/topic/group/" + group.getId();
        log.info("sending group message to destination : {}", destination);

        messagingTemplate.convertAndSend(
                destination,
                response
        );
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
