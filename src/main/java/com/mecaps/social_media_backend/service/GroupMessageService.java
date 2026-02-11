package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.request.GroupMessageRequest;
import com.mecaps.social_media_backend.response.GroupMessageResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;

import java.util.List;

public interface GroupMessageService {

    void sendGroupMessage(Long groupId, GroupMessageRequest groupMessageRequest,
                          User currentUser);

    List<GroupMessageResponse> getGroupMessages(Long groupId);
}
