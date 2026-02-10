package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.Post;
import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
private final FireBaseNotificationService fireBaseNotificationService;
private final PostRepository postRepository;

public void likePost(Long postId , User currentUser){
    Post post = postRepository.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));

    Long ownerId = post.getUser().getId();
    if

}




}
