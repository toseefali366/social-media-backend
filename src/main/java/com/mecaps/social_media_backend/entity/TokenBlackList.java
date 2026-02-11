package com.mecaps.social_media_backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "token_blacklist")
public class TokenBlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(name = "black_listed_token", nullable = false)
    private String blackListedToken;
    private LocalDateTime expiryTime;
}
