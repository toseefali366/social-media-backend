package com.mecaps.social_media_backend.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("Post with Id " + postId + " not found");
    }
}
