package com.mecaps.social_media_backend.Mapper;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserMapper {
    public User convertToUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .bio(userRequest.getBio())
                .email(userRequest.getEmail())
                .dob(userRequest.getDob())
                .userName(userRequest.getUserName())
                .gender(userRequest.getGender())
                .phoneNumber(userRequest.getPhoneNumber())
                .privacySetting(userRequest.getPrivacySetting())
                .country(userRequest.getCountry())
                .password(userRequest.getPassword())
                .location(userRequest.getLocation())
                .build();

    }
}
