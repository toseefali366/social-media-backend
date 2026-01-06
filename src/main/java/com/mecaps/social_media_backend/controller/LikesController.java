package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.response.LikesResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likeOrUnlike(@CurrentUser CustomUserDetail currentUser
            , @RequestBody Long postId){

        String response =likesService.likeOrUnlike(postId , currentUser);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/{postId}/likes")
    public ResponseEntity<List<LikesResponse>> getAllLikesOfPost(
            @PathVariable Long postId) {

        return ResponseEntity.ok(
                likesService.getAllLikes(postId)
        );
    }
}
