package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.request.LikeRequest;
import com.mecaps.social_media_backend.response.LikesResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post-likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/like")
    public ResponseEntity<String> likeOrUnlike(@CurrentUser CustomUserDetail currentUser,
            @RequestBody LikeRequest postId){

        String response =likesService.likeOrUnlike(postId , currentUser);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/like/{postId}")
    public ResponseEntity<List<LikesResponse>> getAllLikesOfPost(
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                likesService.getAllLikes(postId)
        );
    }
}
