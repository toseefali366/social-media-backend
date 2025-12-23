package com.mecaps.social_media_backend.Service;

import org.springframework.web.bind.annotation.RequestParam;

public interface OtpService {
    public void sendOtp(String email);
    public void verifyOtp(String email,String otp);
}
