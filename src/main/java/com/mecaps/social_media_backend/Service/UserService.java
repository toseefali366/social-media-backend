package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Request.UserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> createUser(UserRequest userRequest);
    ResponseEntity<?> searchByUserName(String keyword);
}
