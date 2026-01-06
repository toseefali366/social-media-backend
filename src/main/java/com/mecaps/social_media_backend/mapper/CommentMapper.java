package com.mecaps.social_media_backend.mapper;

import com.mecaps.social_media_backend.entity.Comment;
import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.repository.PostRepository;
import com.mecaps.social_media_backend.request.CommentRequest;
import com.mecaps.social_media_backend.response.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final PostRepository postRepository;

    public Comment convertToComment(CommentRequest commentRequest, User user, Post post) {

        return Comment.builder()
                .text(commentRequest.getText())
                .user(user)
                .post(post)
                .build();
    }
public CommentResponse toCommentResponse(Comment comment){

    return CommentResponse.builder()
            .id(comment.getId())
            .text(comment.getText())
            .createdAt(comment.getCreatedAt())
            .firstName(comment.getUser().getFirstName())
            .lastName(comment.getUser().getLastName())
            .profilePicture(comment.getUser().getProfilePictureUrl())
            .build();
}

}
