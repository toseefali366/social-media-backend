package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CustomUserDetail;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    UserResponse findUserById(Long id);

    UserResponse updateCurrentUser(CustomUserDetail currentUser,
                                   UserRequest request);

    void deleteCurrentUser(CustomUserDetail currentUser);
}
