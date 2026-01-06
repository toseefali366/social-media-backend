package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.request.AuthDTO;
import com.mecaps.social_media_backend.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    AuthResponse authenticateUser(AuthDTO request, HttpServletRequest httpRequest);
}
