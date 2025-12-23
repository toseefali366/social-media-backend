package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.SendOtpRequest;
import com.mecaps.social_media_backend.Request.VerifyOtpRequest;
import com.mecaps.social_media_backend.Service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {
private final OtpService otpService;


    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/sendotp")
    public ResponseEntity<String> sendOtp(@RequestBody SendOtpRequest request){
        otpService.sendOtp(request.getEmail());
        return ResponseEntity.ok("Check Email we have sent you a Otp");

    }
    @PostMapping("/verifyotp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest request){
        otpService.verifyOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok("Email verified successfully");

    }
}
