package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.entity.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostContentRepository extends JpaRepository<PostContent,Long> {
    List<PostContent> findByPost_id(Long id);
}
