package com.mecaps.social_media_backend.Request;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.Privacy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String bio;
    private String lastName;
    private String userName;
    private String email;
    private String location;
    private Country country;
    private Privacy privacySetting;

    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String password;
    private Gender gender;
    private MultipartFile profilePicUrl;


}
