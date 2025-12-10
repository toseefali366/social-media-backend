package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.PrivacySetting;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    @Enumerated(EnumType.STRING)
    private Country country;

    private String coverPictureUrl;

    private LocalDate dob;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Boolean isVerified;

    @Column(nullable = false)
    private String lastName;

    private String location;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private PrivacySetting privacySetting;

    private String profilePictureUrl;

    @CreationTimestamp
    private LocalDateTime signUpDate;

    @Column(unique = true, nullable = false)
    private String userName;
}
