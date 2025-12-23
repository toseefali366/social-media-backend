package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.AuthDTO;
import com.mecaps.social_media_backend.Response.AuthResponse;
import com.mecaps.social_media_backend.Service.AuthService;
import com.mecaps.social_media_backend.ServiceImpl.AuthServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService authService;
@PostMapping("/login")
public ResponseEntity<AuthResponse> login(@RequestBody  AuthDTO authDTO, HttpServletRequest request){
    AuthResponse response = authService.authenticateUser(authDTO,request);
    return ResponseEntity.ok(response);
}

}
