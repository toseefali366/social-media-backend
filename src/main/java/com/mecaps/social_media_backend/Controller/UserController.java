package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.UpdateUserRequest;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.MeResponse;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CurrentUser;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<?> createUser(@ModelAttribute UserRequest userRequest) {

        return userService.createUser(userRequest);
    }
  @GetMapping("/{id}")
    public ResponseEntity<UserResponse>findUserById(@PathVariable Long id) {
     return ResponseEntity.ok(userService.findUserById(id));
  }
    @GetMapping("/me")
    public CustomUserDetail me(@CurrentUser CustomUserDetail user) {
        return user;

    }
    @PutMapping(value = "/update", consumes = "multipart/form-data")
    public ResponseEntity<UserResponse> updateMe(
            @CurrentUser CustomUserDetail currentUser,
            @ModelAttribute UpdateUserRequest request
    ) {
        return ResponseEntity.ok(
                userService.updateCurrentUser(currentUser, request)
        );
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCurrentUser(
            @CurrentUser CustomUserDetail currentUser) {

        userService.deleteCurrentUser(currentUser);
        return ResponseEntity.noContent().build();
    }




}

