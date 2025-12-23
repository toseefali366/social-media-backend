package com.mecaps.social_media_backend.Response;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.PrivacySetting;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    //private String email;
    //private String phoneNumber;
    private String bio;
    private String location;
    private Country country;
    private Gender gender;
    private LocalDate dob;
    private PrivacySetting privacySetting;
    private String profilePictureUrl;
    private String coverPictureUrl;


}
