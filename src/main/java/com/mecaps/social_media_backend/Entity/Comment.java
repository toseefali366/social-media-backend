package com.mecaps.social_media_backend.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Post postId;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)

    private User userId;

}
