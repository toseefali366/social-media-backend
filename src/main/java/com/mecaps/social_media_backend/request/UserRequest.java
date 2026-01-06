package com.mecaps.social_media_backend.request;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.ProfilePrivacy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter

public class UserRequest {
    private String bio;
    private Country country;
    private MultipartFile coverPictureUrl;
    private LocalDate dob;
    private String email;
    private String firstName;
    private Gender gender;
    private String lastName;
    private String location;
    private String password;
    private String phoneNumber;
    private ProfilePrivacy profilePrivacy;
    private MultipartFile profilePictureUrl;
    private String userName;
}
