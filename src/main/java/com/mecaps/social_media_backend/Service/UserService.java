package com.mecaps.social_media_backend.Service;



import com.mecaps.social_media_backend.Request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {

public ResponseEntity<?> createUser(UserRequest request);
}
