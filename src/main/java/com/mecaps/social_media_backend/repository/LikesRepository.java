package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.entity.Likes;
import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {

    boolean existsByUserAndPost(User user, Post post);

    Optional<Likes> findByUser_idAndPost_id(Long userId, Long postID);

    long countByPost(Post post);

    List<Likes> findAllByPost_Id(Long postId);
}
