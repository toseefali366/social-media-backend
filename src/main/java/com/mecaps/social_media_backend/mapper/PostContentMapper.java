package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.PostContent;
import com.mecaps.social_media_backend.request.PostContentRequest;

public class PostContentMapper {
    private PostContentMapper(){}

    public static PostContent toPostContent(PostContentRequest request,Post post, String contentData){
        return PostContent.builder()
                .post(post).contentType(request.getContentType())
                .position(request.getPosition())
                .contentData(contentData)
                .build();
    }
}
