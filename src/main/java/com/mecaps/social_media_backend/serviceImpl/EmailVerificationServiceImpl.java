package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.exception.InvalidOtpException;
import com.mecaps.social_media_backend.exception.UserAlreadyVerifiedException;
import com.mecaps.social_media_backend.exception.UserNotFoundException;
import com.mecaps.social_media_backend.repository.UserRepository;
import com.mecaps.social_media_backend.service.EmailVerificationService;
import com.mecaps.social_media_backend.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {
    private final UserRepository userRepository;
    private final OtpRedisService otpRedisService;
    private final EmailService emailService;


    /* SEND OTP */
    @Override
    public void sendEmailVerificationOtp(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (Boolean.TRUE.equals(user.getIsVerified())) {
            throw new UserAlreadyVerifiedException("User already verified");
        }

        String otp = OtpUtil.generateOtpCode();
        otpRedisService.saveOtp(user.getId(), otp);
        emailService.sendOtpEmail(email, otp);
    }


    /* VERIFY OTP */
    @Override
    public void verifyEmailOtp(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String savedOtp = otpRedisService.getOtp(user.getId());

        if(savedOtp == null || !savedOtp.equals(otp)){
            throw new InvalidOtpException("Invalid or expired OTP");
        }

        user.setIsVerified(true);
        userRepository.save(user);
        otpRedisService.deleteOtp(user.getId());
    }
}
