package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.ChangePasswordDTO;
import com.mecaps.social_media_backend.Request.UpdateUserRequest;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ResponseEntity<?> createUser(UserRequest userRequest);
    ResponseEntity<?> searchByUserName(String keyword);
    UserResponse findUserById(Long id);
    String updatePassword(CustomUserDetail customUserDetail, ChangePasswordDTO request);
    UserResponse updateCurrentUser(CustomUserDetail currentUser,
                                   UpdateUserRequest request);

    void deleteCurrentUser(CustomUserDetail currentUser);
}
