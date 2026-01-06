package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.request.PostRequest;

public class PostRequestMapper {
    private PostRequestMapper() {
        // prevent instantiation
    }
    public static Post toPost(PostRequest postRequest, User user) {
      return Post.builder()
              .text(postRequest.getText())
              .postVisibility(postRequest.getPostVisibility())
              .user(user)
              .build();
    }
}
