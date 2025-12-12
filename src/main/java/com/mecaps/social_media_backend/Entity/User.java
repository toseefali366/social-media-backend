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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bio;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;

    private String location;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Country country;
    private LocalDate dob;

    private Boolean isVerified;
    @CreationTimestamp
    @DateTimeFormat
    private LocalDateTime signUpDate;
    @Enumerated(EnumType.STRING)
    private PrivacySetting privacySetting;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String coverPictureUrl;
    private String profilePictureUrl;


}
