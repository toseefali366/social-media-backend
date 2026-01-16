package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.ForgotPasswordRequest;
import com.mecaps.social_media_backend.Request.VerifyOtpRequest;
import com.mecaps.social_media_backend.Service.ForgotPasswordService;
import com.mecaps.social_media_backend.Request.ResetPasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis-auth")
@RequiredArgsConstructor
public class ForgotPasswordController {
    private final ForgotPasswordService service;

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody ForgotPasswordRequest request
    ) {
        service.sendOtp(request.getIdentifier());
        return ResponseEntity.ok("OTP has been sent successfully");
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
            @RequestBody VerifyOtpRequest request
    ) {
        service.verifyOtp(request.getIdentifier(), request.getOtp());
        return ResponseEntity.ok("OTP verified successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestBody ResetPasswordRequest request
    ) {
        service.resetPassword(
                request.getIdentifier(),
                request.getNewPassword()
        );
        return ResponseEntity.ok("Password reset successful");
    }

}
