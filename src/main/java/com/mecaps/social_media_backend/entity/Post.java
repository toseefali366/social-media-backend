package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.PostVisibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private PostVisibility visibility ;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
