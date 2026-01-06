package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.PostVisbility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    private PostVisbility visibility ;
    @ManyToOne
    private User user;
}
