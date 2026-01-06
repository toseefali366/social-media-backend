package com.mecaps.social_media_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
     private String token;
     private String refreshToken;
     private String message;

}
