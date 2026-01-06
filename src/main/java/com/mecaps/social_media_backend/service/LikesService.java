package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.response.LikesResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikesService {

    String likeOrUnlike(Long post_id , CustomUserDetail currentUser);

    List<LikesResponse> getAllLikes(Long postId);

}
