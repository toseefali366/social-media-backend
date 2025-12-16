package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.MeResponse;
import com.mecaps.social_media_backend.Security.CurrentUser;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

private final UserService userService;
@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<?> createUser(@ModelAttribute UserRequest userRequest) {
return userService.createUser(userRequest);
}

    @GetMapping("/me")
    public MeResponse me(@CurrentUser CustomUserDetail user) {
        return new MeResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }


}
