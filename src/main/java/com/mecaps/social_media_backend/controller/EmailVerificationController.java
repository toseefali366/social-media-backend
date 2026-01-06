package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.request.SendOtpRequest;
import com.mecaps.social_media_backend.request.VerifyEmailOtpRequest;
import com.mecaps.social_media_backend.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/email-auth")
@RequiredArgsConstructor
public class EmailVerificationController {

    private final EmailVerificationService emailVerificationService;

    /* SEND EMAIL OTP */
    @PostMapping("/send-email-otp")
    public ResponseEntity<?> sendOtp(
            @RequestBody SendOtpRequest request) {

        emailVerificationService.sendEmailVerificationOtp(request.getEmail());

        return ResponseEntity.ok(
                Map.of("message", "OTP sent to email")
        );
    }

    /* VERIFY EMAIL OTP */
    @PostMapping("/verify-email-otp")
    public ResponseEntity<?> verifyOtp(
            @RequestBody VerifyEmailOtpRequest request) {

        emailVerificationService.verifyEmailOtp(
                request.getEmail(),
                request.getOtp()
        );

        return ResponseEntity.ok(
                Map.of("message", "Email verified successfully")
        );
    }
}
