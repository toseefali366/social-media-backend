package com.mecaps.social_media_backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserFcmToken {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @Column(length = 1000)
    private String token;
}
