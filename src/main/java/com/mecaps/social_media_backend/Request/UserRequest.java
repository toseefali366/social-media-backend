package com.mecaps.social_media_backend.Request;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.PrivacySetting;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;
    private LocalDate dob;
    private Gender gender;
    private MultipartFile coverPictureUrl;
    private MultipartFile profilePictureUrl;
    private String location;
    private PrivacySetting privacySetting;
    private String bio;
    private Country country;

}
