package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.AuthDTO;
import com.mecaps.social_media_backend.Response.AuthResponse;
import com.mecaps.social_media_backend.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthDTO request, HttpServletRequest httpRequest) {
        AuthResponse response = authService.authenticateUser(request, httpRequest);
        return ResponseEntity.ok(response);
    }

}
