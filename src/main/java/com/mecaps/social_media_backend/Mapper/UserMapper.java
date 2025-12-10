package com.mecaps.social_media_backend.Mapper;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public User convertToUser(UserRequest userRequest) {
        return User.builder()
                .bio(userRequest.getBio())
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .location(userRequest.getLocation())
                .gender(userRequest.getGender())
                .isVerified(userRequest.getIsVerified())
                .phoneNumber(userRequest.getPhoneNumber())
                .privacySetting(userRequest.getPrivacySetting())
                .country(userRequest.getCountry())
                .userName(userRequest.getUserName())
                .dob(userRequest.getDob())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .location(userRequest.getLocation())
                .build();
    }
}
