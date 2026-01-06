package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Comment;
import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.exception.BadRequestException;
import com.mecaps.social_media_backend.exception.UnAuthorizedException;
import com.mecaps.social_media_backend.mapper.CommentMapper;
import com.mecaps.social_media_backend.repository.CommentRepository;
import com.mecaps.social_media_backend.request.CommentRequest;
import com.mecaps.social_media_backend.response.CommentResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.CommentService;
import com.mecaps.social_media_backend.validations.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final Validation validation;

    public CommentResponse createComment(CommentRequest commentRequest, CustomUserDetail currentUser) {
        // fetch current user
        User user = currentUser.getUser();
        // get post
        Post post = validation.getPostById(commentRequest.getPostId());

        Comment comment = CommentMapper.convertToComment(commentRequest, user, post);

        Comment saved = commentRepository.save(comment);
        return CommentMapper.toCommentResponse(saved);
    }

    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest, CustomUserDetail currentUser) {

        // Fetch comment
        Comment comment = validation.getCommentById(commentId);

        // Check commentor
        if (!comment.getUser().getId().equals(currentUser.getUser().getId())) {
            log.warn("User is not the same as the current user not allowed to update comment");
            throw new UnAuthorizedException("You are not allowed to update this comment");
        }

        // Update fields
        if (commentRequest.getText() == null || commentRequest.getText().isBlank()) {
            log.error("Text is empty");
            throw new BadRequestException("Comment text cannot be empty");
        }
        comment.setText(commentRequest.getText());

        Comment updatedComment = commentRepository.save(comment);
        return CommentMapper.toCommentResponse(updatedComment);
    }


    @Override
    public void deleteComment(Long commentId, CustomUserDetail currentUser) {
        Comment comment = validation.getCommentById(commentId);

        if (!comment.getUser().getId().equals(currentUser.getUser().getId())) {
            log.warn("User is not the same as the current user not allowed to delete this comment");
            throw new UnAuthorizedException("You are not allowed to delete this comment");
        }

        commentRepository.delete(comment);


    }

    @Override
    public List<CommentResponse> getAllComment(Long id) {
        Post post = validation.getPostById(id);

        return commentRepository.findByPost_Id(id)
                .stream().map(CommentMapper::toCommentResponse)
                .toList();

    }


}
