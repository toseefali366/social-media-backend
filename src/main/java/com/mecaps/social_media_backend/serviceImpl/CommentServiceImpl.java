package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Comment;
import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.CommentMapper;
import com.mecaps.social_media_backend.repository.CommentRepository;
import com.mecaps.social_media_backend.request.CommentRequest;
import com.mecaps.social_media_backend.response.CommentResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.CommentService;
import com.mecaps.social_media_backend.validations.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final Validation validation;

    public CommentResponse createComment(CommentRequest commentRequest, CustomUserDetail currentUser){
    // fetch current user
        User user = currentUser.getUser();
    // get post
        Post post = validation.getPostById(commentRequest.getPostId());

        Comment comment = commentMapper.convertToComment(commentRequest,user,post);

        Comment saved = commentRepository.save(comment);
    return commentMapper.toCommentResponse(saved);
}

    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest, CustomUserDetail currentUser) {

        // Fetch comment
        Comment comment = validation.getCommentById(commentId);

        // Check commentor
        if (!comment.getUser().getId().equals(currentUser.getUser().getId())) {
            throw new RuntimeException("You are not allowed to update this comment");
        }

        // Update fields
        comment.setText(commentRequest.getText());

        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toCommentResponse(updatedComment);
    }


    @Override
    public void deleteComment(Long commentId, CustomUserDetail currentUser) {
        Comment comment = validation.getCommentById(commentId);

        if (!comment.getUser().getId().equals(currentUser.getUser().getId())) {
            throw new RuntimeException("You are not allowed to delete this comment");
        }

        commentRepository.delete(comment);


        }

    @Override
    public List<CommentResponse> getAllComment(Long id){
Post post = validation.getPostById(id);

return commentRepository.findByPost_Id(id)
        .stream().map(commentMapper::toCommentResponse)
        .toList();

    }



}
