package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.request.GroupRequest;
import com.mecaps.social_media_backend.response.GroupResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import org.springframework.stereotype.Service;


public interface GroupService {

    GroupResponse createGroup(GroupRequest groupRequest, CustomUserDetail currentUser);
}
