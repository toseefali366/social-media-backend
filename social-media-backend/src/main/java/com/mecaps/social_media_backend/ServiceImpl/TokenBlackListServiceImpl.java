package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.TokenBlackList;
import com.mecaps.social_media_backend.Repository.TokenBlackListRepository;
import com.mecaps.social_media_backend.Service.TokenBlackListService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenBlackListServiceImpl implements TokenBlackListService {
    private final TokenBlackListRepository tokenBlackListRepository;


    public void blackListToken(String token, LocalDateTime expiry) {
        if (!tokenBlackListRepository.existsByBlackListedToken(token)) {
            TokenBlackList tokenBlackList = new TokenBlackList();
            tokenBlackList.setBlackListedToken(token);
            tokenBlackList.setExpiryTime(expiry);
            tokenBlackListRepository.save(tokenBlackList);
        }
    }

    public boolean isBlacklisted(String token) {
        return tokenBlackListRepository.existsByBlackListedToken(token);
    }

    @Scheduled(fixedRate = 10 * 60 * 1000)// every 10 minutes
    @Transactional
    public void cleanExpiredTokens() {
        tokenBlackListRepository.deleteByExpiryTimeBefore(LocalDateTime.now());

    }
}

