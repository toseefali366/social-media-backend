package com.mecaps.social_media_backend.Controller;


import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/UserSignUp")
    public ResponseEntity<?> userSignUp(@RequestBody UserRequest userRequest) {
        return userService.userSignUP(userRequest);
    }


}
