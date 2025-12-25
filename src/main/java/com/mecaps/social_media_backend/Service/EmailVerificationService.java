package com.mecaps.social_media_backend.Service;

public interface EmailVerificationService {
    void sendEmailVerificationOtp(String email);
    void verifyEmailOtp(String email, String otp);
}
