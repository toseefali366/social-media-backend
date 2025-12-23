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

    public User convertToUser(UserRequest request) {
        return User.builder()
                .bio(request.getBio())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .location(request.getLocation())
                .gender(request.getGender())
                .phoneNumber(request.getPhoneNumber())
                .privacySetting(request.getPrivacySetting())
                .country(request.getCountry())
                .userName(request.getUserName())
                //.dateOfbirth(request.getDateOfBirth())
                .password(passwordEncoder.encode(request.getPassword())).build();
    }
}
