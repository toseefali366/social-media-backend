package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Likes;
import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.LikesMapper;
import com.mecaps.social_media_backend.repository.LikesRepository;
import com.mecaps.social_media_backend.repository.PostRepository;
import com.mecaps.social_media_backend.response.LikesResponse;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.LikesService;
import com.mecaps.social_media_backend.validations.Validation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j


public class    LikesServiceImpl implements LikesService {
    private final LikesRepository likesRepository;
    private final PostRepository postRepository;
    private final Validation validation;
    private final LikesMapper likesMapper;

    // private static final Logger logger = LoggerFactory.getLogger(LikesServiceImpl.class);

    @Override
    public String likeOrUnlike(Long postId, CustomUserDetail currentUser) {

        User user = currentUser.getUser();

        Post post = validation.getPostById(postId);

        Optional<Likes> existingLike = likesRepository.findByUser_idAndPost_id(user.getId(), post.getId());

        if (existingLike.isPresent()) {
            likesRepository.delete(existingLike.get());

           log.info("Post unliked| userId ={} | postId={} " , user.getId() , postId);
            return "Post unliked ";
        }

        Likes like = Likes.builder()
                .user(user)
                .post(post)
                .build();

        likesRepository.save(like);
        log.info("Post liked | userId={} | postId={}",
                user.getId(), postId);

        return "Post Liked";
    }


    @Override
    public List<LikesResponse> getAllLikes(Long postId) {

      Post post =  validation.getPostById(postId);

        return likesRepository.findAllByPost_Id(postId)
                .stream()
                .map(likesMapper::toLikesResponse)
                .toList();
    }
}
