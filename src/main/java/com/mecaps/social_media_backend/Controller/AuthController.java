package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.InvalidCredentials;
import com.mecaps.social_media_backend.Exception.UserNotFoundException;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.AuthDTO;
import com.mecaps.social_media_backend.Security.JwtService;
import com.mecaps.social_media_backend.Service.TokenBlackListService;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenBlackListService tokenBlackListService;


    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, TokenBlackListService tokenBlackListService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

        this.tokenBlackListService = tokenBlackListService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthDTO request) {
        String identifier = request.getIdentifier();
        User user = userRepository.findByEmailOrUserNameOrPhoneNumber(identifier,identifier,identifier).orElseThrow(()->new InvalidCredentials("Invalid Credentials Please Enter Again"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentials("Invalid Password");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        Map<String, String> authResponse = new HashMap<>();
        authResponse.put("AccessToken : ", accessToken);
        authResponse.put("RefreshToken : ", refreshToken);
        return authResponse;

    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader){
String token = authHeader.substring(7);
        LocalDateTime expiry = jwtService.extractExpiry(token);

        tokenBlackListService.blackListToken(token,expiry);
        return ResponseEntity.ok("Logged out successfully");
    }
}