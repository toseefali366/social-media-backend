package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.entity.Post;
<<<<<<< HEAD
import com.mecaps.social_media_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserAndIsDeletedFalse(User user);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
>>>>>>> 348ccd2a1d9a44012660e812a8dcdb94c685ec89
}
