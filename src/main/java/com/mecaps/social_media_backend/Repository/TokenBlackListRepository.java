package com.mecaps.social_media_backend.Repository;

import com.mecaps.social_media_backend.Entity.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackList, Long> {

    Optional<TokenBlackList> findByBlackListedToken(String Token);

    boolean existsByBlackListedToken(String Token);

    void deleteByExpiryTimeBefore(LocalDateTime now);

}
