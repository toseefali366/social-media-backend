package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Request.AuthDTO;
import com.mecaps.social_media_backend.Response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    AuthResponse authenticateUser(AuthDTO request, HttpServletRequest httpRequest);
}
