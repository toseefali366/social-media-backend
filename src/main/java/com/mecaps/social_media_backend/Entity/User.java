package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.Country;
import com.mecaps.social_media_backend.Enum.Gender;
import com.mecaps.social_media_backend.Enum.Privacy;
import jakarta.persistence.*;
import lombok.*;

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
private Long Id;

private String bio;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private Country country;

private String coverPicUrl;

private LocalDate dateOfBirth;

@Column(nullable = false,unique = true)
private String email;

@Column(nullable = false)
private String firstName;
@Column(nullable = false)
private String lastName;

@Column(nullable = false,unique = true)
private String userName;

@Enumerated(EnumType.STRING)
private Gender gender;

@Column(nullable = false)
    private boolean isVerified = false;

private String location;
@Column(nullable = false)
private String password;
@Column(nullable = false)
private String phoneNumber;

@Enumerated(EnumType.STRING)
private Privacy privacySetting;

private String profilePicUrl;

private LocalDateTime Signup;



}
