package com.mecaps.social_media_backend.Mapper;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.UserResponse;
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
                .phoneNumber(userRequest.getPhoneNumber())
                .privacySetting(userRequest.getPrivacySetting())
                .country(userRequest.getCountry())
                .userName(userRequest.getUserName())
                .dob(userRequest.getDob())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
    }
    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
              //  .email(user.getEmail())
                //.phoneNumber(user.getPhoneNumber())
                .bio(user.getBio())
                .location(user.getLocation())
                .country(user.getCountry())
                .gender(user.getGender())
                .dob(user.getDob())
                .privacySetting(user.getPrivacySetting())
                .profilePictureUrl(user.getProfilePictureUrl())
                .coverPictureUrl(user.getCoverPictureUrl())
                .build();
    }

}
