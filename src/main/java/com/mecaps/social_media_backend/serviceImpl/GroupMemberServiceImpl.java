package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.Enum.Role;
import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.GroupMember;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.GroupMemberMapper;
import com.mecaps.social_media_backend.repository.GroupMemberRepository;
import com.mecaps.social_media_backend.repository.GroupRepository;
import com.mecaps.social_media_backend.request.GroupMemberRequest;
import com.mecaps.social_media_backend.response.GroupMemberResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.GroupMemberService;
import com.mecaps.social_media_backend.validations.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupMemberServiceImpl implements GroupMemberService {
    private final GroupMemberMapper groupMemberMapper;
    private final Validation validation;
    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;

    @Override
    public GroupMemberResponse addMember(Long groupId, GroupMemberRequest groupMemberRequest, CustomUserDetail currentUser) {
        // fetching group with group ID
        Group group = validation.getGroupById(groupId);

        // fetching current user
        User currentUserId = validation.getUserById(currentUser.getUser().getId());

        //checking if the Creator is a currentUser
        Boolean isCreator = group.getCreatedBy().getId().equals(currentUserId);

        // checking admin is present
        Boolean isAdmin = groupMemberRepository.findByGroupIdAndUserIdAndRole(
                group.getId(), currentUserId.getId(), Role.ADMIN).isPresent();

        // checking one who adding is admin or not
        if (!isCreator && !isAdmin) {
            throw new RuntimeException("You are not allowed to add member");
        }

        // checking user who is added in group present or not
        User member = validation.getUserById(groupMemberRequest.getUserId());

        // checking if user is already added to group or not
        if (groupMemberRepository.existsByGroupAndUser(group, member)) {
            throw new IllegalStateException("User is already a member");
        }

        //Creating member
        GroupMember createMember = groupMemberMapper.toGroupMember(group, member);
        group.addMember(createMember);

        // saving member
        GroupMember savedMember = groupMemberRepository.save(createMember);
        return groupMemberMapper.toGroupMemberResponse(savedMember);

    }
}
