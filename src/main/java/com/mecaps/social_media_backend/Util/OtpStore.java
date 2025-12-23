package com.mecaps.social_media_backend.Util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class OtpStore {
    private final Map<String,OtpData>otpMap = new ConcurrentHashMap<>();

    public void saveOtp(String email,String otp){
        otpMap.put(email,new OtpData(otp, LocalDateTime.now().plusMinutes(5)));
    }
    public boolean validateOtp(String email , String otp){
        OtpData otpData = otpMap.get(email);
        if(otpData==null)return  false;

        if(otpData.getExpiryTime().isBefore(LocalDateTime.now())){
            otpMap.remove(email);
                    return false;
        }
        boolean isValid = otpData.getOtp().equals(otp);

        if(isValid){
            otpMap.remove(email);
        }
return isValid;
    }

}
