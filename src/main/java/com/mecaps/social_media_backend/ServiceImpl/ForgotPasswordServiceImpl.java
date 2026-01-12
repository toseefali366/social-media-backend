package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.InvalidOtpException;
import com.mecaps.social_media_backend.Exception.UserNotFoundException;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Service.ForgotPasswordService;
import com.mecaps.social_media_backend.Utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
    private final UserRepository userRepository;
    private final OtpRedisService otpRedisService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public void sendOtp(String identifier){
     User user = userRepository.findByEmailOrUserNameOrPhoneNumber(identifier,identifier,identifier)
             .orElseThrow(()-> new UserNotFoundException("User not found"));
     String otp = OtpUtil.generateOtpCode();
     otpRedisService.saveOtp(user.getId(), otp);
     emailService.sendOtpEmail(user.getEmail(), otp);
    }

    public void verifyOtp(String identifier, String otp){
        User user = userRepository.findByEmailOrUserNameOrPhoneNumber(identifier,identifier,identifier)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        String savedOtp = otpRedisService.getOtp(user.getId());
        if(savedOtp == null || !savedOtp.equals(otp)){
            throw new InvalidOtpException("Invalid or expired OTP");
        }
    }

    public void resetPassword(String identifier ,String newPassword){
        User user =  userRepository.findByEmailOrUserNameOrPhoneNumber(identifier,identifier,identifier)
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        otpRedisService.deleteOtp(user.getId());
    }
}
