package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Exception.UserAlreadyExistsException;
import com.mecaps.social_media_backend.Request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> userSignUP(UserRequest userRequest);


}
