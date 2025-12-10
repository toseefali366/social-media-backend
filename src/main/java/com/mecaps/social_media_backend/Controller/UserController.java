package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<?> createUser(@ModelAttribute UserRequest userRequest) {

    return userService.createUser(userRequest);
}
}
