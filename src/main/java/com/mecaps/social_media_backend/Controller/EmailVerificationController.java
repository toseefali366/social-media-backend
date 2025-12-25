package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.SendOtpRequest;
import com.mecaps.social_media_backend.Request.VerifyEmailOtpRequest;
import com.mecaps.social_media_backend.Service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
