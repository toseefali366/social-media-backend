package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.MeResponse;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CurrentUser;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> createUser(
            @ModelAttribute UserRequest userRequest) {

        UserResponse response = userService.createUser(userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/me")
    public MeResponse me(@CurrentUser CustomUserDetail customUserDetail) {
        User user = customUserDetail.getUser();
        return new MeResponse(user.getId(),
                user.getUserName(),
                user.getEmail());
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> updateMe(
            @CurrentUser CustomUserDetail currentUser,
            @ModelAttribute UserRequest request) {

        UserResponse response = userService.updateCurrentUser(currentUser, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCurrentUser(
            @CurrentUser CustomUserDetail currentUser) {

        userService.deleteCurrentUser(currentUser);
        return ResponseEntity.noContent().build();
    }


}

