package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.GroupMember;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.GroupMapper;
import com.mecaps.social_media_backend.mapper.GroupMemberMapper;
import com.mecaps.social_media_backend.repository.GroupMemberRepository;
import com.mecaps.social_media_backend.repository.GroupRepository;
import com.mecaps.social_media_backend.request.GroupRequest;
import com.mecaps.social_media_backend.response.GroupResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.GroupService;
import com.mecaps.social_media_backend.validations.Validation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final GroupMapper groupMapper;
    private final Validation validation;
    private final GroupMemberMapper groupMemberMapper;

@Transactional
    @Override
    public GroupResponse createGroup(GroupRequest groupRequest, CustomUserDetail currentUser) {

        User creator = validation.getUserById(currentUser.getUser().getId());

        Group group = groupMapper.toGroup(groupRequest, creator);
        Group saveGroup = groupRepository.saveAndFlush(group);

        GroupMember admin = groupMemberMapper.toGroupAdmin(group,creator);
        GroupMember saveMember = groupMemberRepository.save(admin);

        return groupMapper.toGroupResponse(saveGroup);
    }

}
