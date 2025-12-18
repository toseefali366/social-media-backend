package com.mecaps.social_media_backend.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class OtpRedisServiceImpl{
    private final StringRedisTemplate redisTemplate;
    private static final Duration OTP_TTL = Duration.ofMinutes(5);

    public void saveOtp(Long userId, String otp){
        redisTemplate.opsForValue()
                .set("forgot:otp:"+ userId,otp,OTP_TTL);
    }

    public String getOtp(Long userId){
        return redisTemplate.opsForValue()
                .get("forgot:otp:"+ userId);
    }
    public void deleteOtp(Long userId){
        redisTemplate.delete("forgot:otp:"+ userId);
    }
}


