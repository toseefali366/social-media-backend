package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Request.ChangePasswordDTO;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(UserRequest userRequest);
    ResponseEntity<?> searchByUserName(String keyword);
    String updatePassword(CustomUserDetail customUserDetail, ChangePasswordDTO request);
}
