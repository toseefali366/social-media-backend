package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.AccountStatus;
import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.ProfilePrivacy;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    private Boolean isVerified = false;
    
    @Column(nullable = false)
    private String lastName;

    private String location;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private ProfilePrivacy profilePrivacy = ProfilePrivacy.PUBLIC;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus = AccountStatus.ACTIVE;


    private String profilePictureUrl;

    @CreationTimestamp
    private LocalDateTime signUpDate;

    @Column(unique = true, nullable = false, name = "user_name")
    private String userName;
}
