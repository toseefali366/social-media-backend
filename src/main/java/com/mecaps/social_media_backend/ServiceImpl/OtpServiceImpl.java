package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.UserNotFoundException;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Service.EmailService;
import com.mecaps.social_media_backend.Service.OtpService;
import com.mecaps.social_media_backend.Util.OtpStore;
import com.mecaps.social_media_backend.Util.OtpUtil;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {
private final OtpStore otpStore;
private  final EmailService emailService;
private  final UserRepository userRepository;

    public OtpServiceImpl(OtpStore otpStore, EmailService emailService, UserRepository userRepository) {
        this.otpStore = otpStore;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    public void sendOtp(String email) {
        String otp = OtpUtil.generateOtpCode();
        otpStore.saveOtp(email, otp);
        emailService.sendOtp(email, otp);
    }
    public void verifyOtp(String email,String otp) {
        boolean valid = otpStore.validateOtp(email, otp);

        if (!valid) {
            throw new RuntimeException("Inavlid or Otp Expired");
        }
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setVerified(true);
                userRepository.save(user);
    }


    }

