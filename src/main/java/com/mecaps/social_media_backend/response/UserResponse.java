package com.mecaps.social_media_backend.response;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.ProfilePrivacy;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private Country country;
    private Gender gender;
    private LocalDate dob;
<<<<<<< HEAD
    private ProfilePrivacy profilePrivacy;
=======
    private ProfilePrivacy privacySetting;
>>>>>>> 348ccd2a1d9a44012660e812a8dcdb94c685ec89
    private String profilePictureUrl;
    private String coverPictureUrl;


}