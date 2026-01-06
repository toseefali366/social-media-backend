package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.request.CommentRequest;
import com.mecaps.social_media_backend.response.CommentResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;

    @PostMapping("/createComment")
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest,
                                         @CurrentUser CustomUserDetail currentUser) {
        return commentService.createComment(commentRequest, currentUser);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal CustomUserDetail currentUser
    ) {
        CommentResponse response = commentService.updateComment(commentId, request, currentUser);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal CustomUserDetail currentUser
    ) {
        commentService.deleteComment(commentId, currentUser);
        return ResponseEntity.ok("Comment deleted successfully");
    }


    @GetMapping("/getAll/{postId}")
    public ResponseEntity<List<CommentResponse>> getAllComments(
            @PathVariable Long postId) {

        List<CommentResponse> comments = commentService.getAllComment(postId);
        return ResponseEntity.ok(comments);
    }
}
