package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.UserAlreadyVerifiedException;
import com.mecaps.social_media_backend.Exception.UserNotFoundException;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
private final JavaMailSender mailSender;
private final UserRepository userRepository;
    public EmailServiceImpl(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    @Override
    public void sendOtp(String to, String otp) {
        User user = userRepository.findByEmail(to).orElseThrow(()->new UserNotFoundException("User not Foud"));
        if (Boolean.TRUE.equals(user.isVerified())) {
            throw new UserAlreadyVerifiedException("User Already Verified");
        }
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Email Verification OTP");
            message.setText("Your OTP is " + otp + "   Valid for 5 minutes.");
            mailSender.send(message);

    }


}

