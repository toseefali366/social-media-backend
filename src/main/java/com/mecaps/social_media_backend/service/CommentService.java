package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.request.CommentRequest;
import com.mecaps.social_media_backend.response.CommentResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentRequest request, CustomUserDetail currentUser);
    CommentResponse updateComment(Long commentId, CommentRequest request, CustomUserDetail currentUser);
    void deleteComment(Long commentId, CustomUserDetail currentUser);
    List<CommentResponse> getAllComment(Long postId);
}
