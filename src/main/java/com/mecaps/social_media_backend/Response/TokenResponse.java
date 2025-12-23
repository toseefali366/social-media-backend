package com.mecaps.social_media_backend.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
private String accessToken;
private String refreshToken;
}
