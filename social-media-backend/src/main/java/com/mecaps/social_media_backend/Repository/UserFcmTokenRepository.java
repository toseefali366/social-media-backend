package com.mecaps.social_media_backend.Repository;

import com.mecaps.social_media_backend.Entity.UserFcmToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFcmTokenRepository extends JpaRepository<UserFcmToken,Long> {

    List<UserFcmToken> findByUserId(Long userId);

}
