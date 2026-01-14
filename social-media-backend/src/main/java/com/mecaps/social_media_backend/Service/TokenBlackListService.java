package com.mecaps.social_media_backend.Service;

import java.time.LocalDateTime;

public interface TokenBlackListService {
    void blackListToken(String token , LocalDateTime expiry);
    boolean isBlacklisted(String token);
    void cleanExpiredTokens();
}
