package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.Visibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    private String text;
    @Enumerated(EnumType.STRING)
    private Visibility visibility ;
    @ManyToOne
    private User user;
}
