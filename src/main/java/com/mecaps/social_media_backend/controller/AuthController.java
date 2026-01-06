package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.request.AuthDTO;
import com.mecaps.social_media_backend.response.AuthResponse;
import com.mecaps.social_media_backend.security.JwtService;
import com.mecaps.social_media_backend.service.AuthService;
import com.mecaps.social_media_backend.service.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final TokenBlackListService tokenBlackListService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody  AuthDTO authDTO, HttpServletRequest request){
    AuthResponse response = authService.authenticateUser(authDTO,request);
    return ResponseEntity.ok(response);
}

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {

        if (!authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid Authorization header");
        }

        String token = authHeader.substring(7); // REMOVE Bearer ONLY ONCE

        LocalDateTime expiry = jwtService.extractExpiry(token);
        tokenBlackListService.blackListToken(token, expiry);

        return ResponseEntity.ok("Logged out successfully");
    }

}
