package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

}
