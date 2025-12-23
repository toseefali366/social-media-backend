package com.mecaps.social_media_backend.Util;

import java.security.SecureRandom;

public class OtpUtil {

    private static final SecureRandom random = new SecureRandom();

    private static final int OTP_LENGTH = 6;

    public static String generateOtp(){

        StringBuilder otp = new StringBuilder(OTP_LENGTH);
            for (int i = 0; i < OTP_LENGTH; i++) {
                otp.append(random.nextInt(10));
            }
            return otp.toString();
        }

}

// One more Method to generateOtp

//public static String generateOtp() {
//    return String.valueOf(100000 + secureRandom.nextInt(900000));
//}