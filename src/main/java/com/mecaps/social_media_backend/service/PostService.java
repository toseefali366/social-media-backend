package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.request.PostRequest;
import com.mecaps.social_media_backend.response.PostResponse;

public interface PostService {
    PostResponse createPost(PostRequest request, User user);

    PostResponse getPostById(Long postId);

    PostResponse updatePost(Long id, PostRequest request, User user);

    void deletePost(Long postId, User user);
}
