package com.mecaps.social_media_backend.Repository;

import com.mecaps.social_media_backend.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost_Id(Long postId);
    List<Comment> findByUser_Id(Long userId);
}