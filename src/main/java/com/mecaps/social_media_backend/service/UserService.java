package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.request.ChangePasswordDTO;
import com.mecaps.social_media_backend.request.UserGetRequest;
import com.mecaps.social_media_backend.request.UserRequest;
import com.mecaps.social_media_backend.response.UserResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    UserResponse findUserById(UserGetRequest id);

    UserResponse updateCurrentUser(CustomUserDetail currentUser,
                                   UserRequest request);

    void deleteCurrentUser(CustomUserDetail currentUser);
    List<UserResponse> searchByUserName(String keyword);
    String updatePassword(CustomUserDetail customUserDetail, ChangePasswordDTO request);
}
