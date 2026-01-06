package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.Likes;
import com.mecaps.social_media_backend.response.LikesResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Builder
public class LikesMapper {

    public LikesResponse toLikesResponse(Likes likes) {
        return LikesResponse.builder()
                .id(likes.getId())
                .postId(likes.getPost().getId())
                .userId(likes.getUser().getId())
                .userName(likes.getUser().getUserName())
                .firstName(likes.getUser().getFirstName())
                .lastName(likes.getUser().getLastName())
                .profilePictureUrl(likes.getUser().getProfilePictureUrl())
                .likedAt(likes.getLikedAt())
                .build();

    }
}
