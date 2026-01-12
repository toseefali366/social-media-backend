package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.PasswordDoesNotMatchException;
import com.mecaps.social_media_backend.Exception.UserNotFoundException;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.AuthDTO;
import com.mecaps.social_media_backend.Response.AuthResponse;
import com.mecaps.social_media_backend.Security.JwtService;
import com.mecaps.social_media_backend.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final LocationService locationService;

    public AuthResponse authenticateUser(AuthDTO request, HttpServletRequest httpRequest) {

        String identifier = request.getIdentifier();

        User user = userRepository.findByEmailOrUserNameOrPhoneNumber(identifier
                        , identifier
                        , identifier)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordDoesNotMatchException("Passwords don't match");
        }

        locationService.saveOrUpdateLocation(user, httpRequest);
        String token = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        return new AuthResponse(token, refreshToken,"Login successful");
    }

}
