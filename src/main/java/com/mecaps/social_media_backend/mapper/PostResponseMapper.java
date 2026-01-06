package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.PostContent;
import com.mecaps.social_media_backend.response.PostContentResponse;
import com.mecaps.social_media_backend.response.PostResponse;
import com.mecaps.social_media_backend.response.UserSummaryResponse;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PostResponseMapper {
    private PostResponseMapper() {
    }

    public static PostResponse toPostResponse(Post post, List<PostContent> contents) {
        return PostResponse.builder()
                .id(post.getId())
                .text(post.getText())
                .postVisibility(post.getPostVisibility())
                .createdAt(post.getCreatedAt())
                .user(mapUser(post))
                .content(mapContent(contents))
                .build();
    }

    private static UserSummaryResponse mapUser(Post post) {
        return UserSummaryResponse.builder()
                .id(post.getUser().getId())
                .profileImageUrl(post.getUser().getProfilePictureUrl())
                .fullName(post.getUser().getFirstName() + " " + post.getUser().getLastName())
                .build();
    }

    private static List<PostContentResponse> mapContent(List<PostContent> contents) {
        return contents
                .stream()
                .map(content -> {
                            return PostContentResponse.builder()
                                    .id(content.getId())
                                    .contentType(content.getContentType())
                                    .contentData(content.getContentData())
                                    .position(content.getPosition())
                                    .build();
                        }
                )
                .collect(Collectors.toList());
    }

    public static List<PostResponse> toPostResponseList(
            List<Post> posts,
            Function<Long, List<PostContent>> contentFetcher) {
        return posts.stream()
                .map(post -> toPostResponse
                        (post, contentFetcher.apply(post.getId())))
                .collect(Collectors.toList());
    }


}
