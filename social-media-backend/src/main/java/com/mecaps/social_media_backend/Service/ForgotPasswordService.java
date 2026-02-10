package com.mecaps.social_media_backend.Service;

public interface ForgotPasswordService {
    void sendOtp(String identifier);
    void verifyOtp(String identifier, String otp);
    void resetPassword(String identifier ,String newPassword);
}
