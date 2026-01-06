package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.exception.PasswordDoesNotMatchException;
import com.mecaps.social_media_backend.exception.UserNotFoundException;
import com.mecaps.social_media_backend.repository.UserRepository;
import com.mecaps.social_media_backend.request.AuthDTO;
import com.mecaps.social_media_backend.response.AuthResponse;
import com.mecaps.social_media_backend.security.JwtService;
import com.mecaps.social_media_backend.service.AuthService;
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
