package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.PostResponseMapper;
import com.mecaps.social_media_backend.mapper.UserMapper;
import com.mecaps.social_media_backend.request.ChangePasswordDTO;
import com.mecaps.social_media_backend.request.UserRequest;
import com.mecaps.social_media_backend.response.UserResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.ProfileVisibilityService;
import com.mecaps.social_media_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final ProfileVisibilityService visibilityService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> createUser(
            @ModelAttribute UserRequest userRequest) {

        UserResponse response = userService.createUser(userRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> findUserById(
            @PathVariable Long id) {

        UserResponse response = userService.findUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(
            @CurrentUser CustomUserDetail currentUser) {

        return ResponseEntity.ok(
                userMapper.toUserResponse(currentUser.getUser())
        );
    }

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponse> updateMe(
            @CurrentUser CustomUserDetail currentUser,
            @ModelAttribute UserRequest request) {

        UserResponse response = userService.updateCurrentUser(currentUser, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCurrentUser(
            @CurrentUser CustomUserDetail currentUser) {

        userService.deleteCurrentUser(currentUser);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchByUserName(
            @RequestParam String keyword) {

        List<UserResponse> users = userService.searchByUserName(keyword);
        return ResponseEntity.ok(users);
    }


    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @CurrentUser CustomUserDetail customUserDetail,
            @RequestBody ChangePasswordDTO request
    ) {
        String response = userService.updatePassword(customUserDetail, request);
        return ResponseEntity.ok(response);
    }


}

