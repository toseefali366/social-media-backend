package com.mecaps.social_media_backend.Request;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.PrivacySetting;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateUserRequest {
    private String bio;
    private String firstName;
    private String lastName;
    private String location;
    private String phoneNumber;
    private LocalDate dob;
    private Country country;
    private Gender gender;
    private String userName;
    private PrivacySetting privacySetting;
    private MultipartFile profilePictureUrl;
    private MultipartFile coverPictureUrl;
}
