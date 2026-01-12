package com.mecaps.social_media_backend.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyOtpRequest {
    private String identifier;
    private String otp;
}
