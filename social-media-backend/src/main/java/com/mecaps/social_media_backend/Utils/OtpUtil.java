package com.mecaps.social_media_backend.Utils;

import java.security.SecureRandom;

public class OtpUtil {
    private static  final SecureRandom random = new SecureRandom();
    public static String generateOtpCode() {
        return String.valueOf(100000 + random.nextInt(900000));
    }
}
