package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.PostContent;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.exception.BadRequestException;
import com.mecaps.social_media_backend.exception.UnAuthorizedException;
import com.mecaps.social_media_backend.mapper.PostContentMapper;
import com.mecaps.social_media_backend.mapper.PostRequestMapper;
import com.mecaps.social_media_backend.mapper.PostResponseMapper;
import com.mecaps.social_media_backend.repository.PostContentRepository;
import com.mecaps.social_media_backend.repository.PostRepository;
import com.mecaps.social_media_backend.request.PostContentRequest;
import com.mecaps.social_media_backend.request.PostRequest;
import com.mecaps.social_media_backend.response.PostResponse;
import com.mecaps.social_media_backend.service.PostService;
import com.mecaps.social_media_backend.validations.Validation;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostContentRepository postContentRepository;
    private final Validation validation;

    @Override
    public PostResponse createPost(PostRequest postRequest, User user) {
        Post post = PostRequestMapper.toPost(postRequest, user);
        postRepository.save(post);

        if (postRequest.getContents() != null) {
            for (PostContentRequest postContentRequest : postRequest.getContents()) {
                String contentData;
                contentData = validation.saveImage(postContentRequest.getFile(), "posts");
                PostContent postContent = PostContentMapper.toPostContent(postContentRequest, post, contentData);
                postContentRepository.save(postContent);
            }
        }
        return PostResponseMapper.toPostResponse(post, postContentRepository.findByPost_id(post.getId()));
    }

    @Override
    public PostResponse getPostById(Long postId) {
        Post post = validation.getPostById(postId);
        List<PostContent> postContents = postContentRepository.findByPost_id(post.getId());
        return PostResponseMapper.toPostResponse(post, postContents);
    }

    @Override
    public PostResponse updatePost(Long postId, PostRequest request, User user) {
        Post post = validation.getPostById(postId);
        if (!post.getUser().getId().equals(user.getId())) {
            throw new UnAuthorizedException("You are not allowed to update post");
        }
        if(request.getText() != null) {
            post.setText(request.getText());
        }
        if(request.getPostVisibility() != null) {
            post.setPostVisibility(request.getPostVisibility());
        }
        if(request.getContents() != null && !request.getContents().isEmpty()) {
            throw new BadRequestException("Media cannot be updated");
        }
        return PostResponseMapper.toPostResponse(post, postContentRepository.findByPost_id(post.getId()));
    }

    @Override
    public void deletePost(Long postId, User user) {
        Post post = validation.getPostById(postId);
        if (!post.getUser().getId().equals(user.getId())) {
            log.warn("You are not allowed to delete post");
            throw new UnAuthorizedException("You are not allowed to delete this post");
        }
        List<PostContent> postContents = postContentRepository.findByPost_id(post.getId());
        for (PostContent postContent : postContents) {
            validation.deleteImage(postContent.getContentData());
        }
        postContentRepository.deleteAll(postContents);
        postRepository.delete(post);
    }



}
