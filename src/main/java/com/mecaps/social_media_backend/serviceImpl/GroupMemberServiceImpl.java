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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;
    private final GroupMemberMapper groupMemberMapper;
    private final Validation validation;

    @Override
    public GroupMemberResponse addMember(Long groupId, GroupMemberRequest groupMemberRequest,
                                         CustomUserDetail currentUser) {
        // Fetching Group with groupId
        Group group = validation.getGroupById(groupId);

        // Fetching currentUser
        User currentUserId = validation.getUserById(currentUser.getUser().getId());

        // Checking is currenUser is Admin Or Not
        Boolean isCreator = group.getCreatedBy().getId().equals(currentUserId);

        // Fetching Admin
        Boolean isAdmin = groupMemberRepository.findByGroupIdAndUserIdAndRole(
                group.getId(), currentUserId.getId(), Role.ADMIN).isPresent();

        // Checking the one who is adding is admin or not
        if (!isCreator && !isAdmin) {
            throw new RuntimeException("You are not allowed to add member");
        }
        // checking the user is existed or not
        User member = validation.getUserById(groupMemberRequest.getUserId());

        // checking is user is already added to group or not
        if (groupMemberRepository.existsByGroupAndUser(group, member)) {
            throw new IllegalArgumentException("User already a member");
        }

        // Creating Member
        GroupMember createMember = groupMemberMapper.toGroupMember(group, member);
        group.addMember(createMember);

        // Saved Member
        GroupMember savedMember = groupMemberRepository.save(createMember);
        return groupMemberMapper.toGroupMemberResponse(createMember);
    }

}
