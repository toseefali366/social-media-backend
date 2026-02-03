package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.request.GroupMemberRequest;
import com.mecaps.social_media_backend.response.GroupMemberResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import org.springframework.stereotype.Service;


public interface GroupMemberService {
    GroupMemberResponse addMember(Long groupId, GroupMemberRequest groupMemberRequest, CustomUserDetail currentUser);

}
