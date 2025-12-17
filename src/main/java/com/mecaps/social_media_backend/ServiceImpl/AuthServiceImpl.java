package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.AuthDTO;
import com.mecaps.social_media_backend.Response.AuthResponse;
import com.mecaps.social_media_backend.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl {
 private final UserRepository userRepository;
 private final PasswordEncoder passwordEncoder;
 private final JwtService jwtService;



    public AuthResponse login(AuthDTO request) {

    String identifier = request.getIdentifier();

    User user = userRepository.findByEmailOrUserNameOrPhoneNumber(identifier,identifier,identifier)
            .orElseThrow(()->new RuntimeException("User not found"));

     if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
          throw new RuntimeException("Wrong password");
     }

    String token = jwtService. generateAccessToken(user);
     return new AuthResponse(token,"Login successful");


    }
}
