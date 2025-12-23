package com.mecaps.social_media_backend.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor

public class OtpData {
    private String otp;
    private LocalDateTime expiryTime;
}
