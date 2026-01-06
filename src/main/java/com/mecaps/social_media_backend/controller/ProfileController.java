package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.PostResponseMapper;
import com.mecaps.social_media_backend.repository.PostContentRepository;
import com.mecaps.social_media_backend.response.PostResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.ProfileVisibilityService;
import com.mecaps.social_media_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ProfileVisibilityService visibilityService;
    private final PostContentRepository postContentRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<?> viewProfile(
            @CurrentUser CustomUserDetail customUserDetail,
            @PathVariable Long userId
    ) {
        User viewer = customUserDetail.getUser();
        // 1️ Load profile owner as ENTITY (important)
        User profileOwner = userService.getUserById(userId);

        //  Can viewer see this profile at all?
        if (!visibilityService.canViewProfile(viewer, profileOwner)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("User not available");
        }

        //  Get only visible posts (privacy + friendship handled inside)
        List<Post> visiblePosts =
                visibilityService.getVisiblePosts(viewer, profileOwner);

        //  Map posts → responses (with contents)
        List<PostResponse> response =
                PostResponseMapper.toPostResponseList(
                        visiblePosts,
                        postContentRepository::findByPost_id
                );

        //  Return response
        return ResponseEntity.ok(response);
    }
}
