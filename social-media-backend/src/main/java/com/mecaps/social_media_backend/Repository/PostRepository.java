package com.mecaps.social_media_backend.Repository;

import com.mecaps.social_media_backend.Entity.Post;
import com.mecaps.social_media_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserAndIsDeletedFalse(User user);

}