package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.request.PostRequest;
import com.mecaps.social_media_backend.response.PostResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostResponse> createPost(
            @ModelAttribute PostRequest request,
            @CurrentUser CustomUserDetail customUserDetail) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.createPost(request, customUserDetail.getUser()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId, @RequestBody PostRequest request, @CurrentUser CustomUserDetail customUserDetail) {
        return ResponseEntity.ok(postService.updatePost(postId, request, customUserDetail.getUser()));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<PostResponse> deletePost(
            @PathVariable Long postId, @CurrentUser CustomUserDetail customUserDetail){
            postService.deletePost(postId, customUserDetail.getUser());
            return ResponseEntity.noContent().build();
    }
}

