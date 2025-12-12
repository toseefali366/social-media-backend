package com.mecaps.social_media_backend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likesId;

    @CreationTimestamp
    @DateTimeFormat
    private LocalDateTime likedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;
}
