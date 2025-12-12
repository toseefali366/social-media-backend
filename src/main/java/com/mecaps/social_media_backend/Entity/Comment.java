package com.mecaps.social_media_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @CreationTimestamp
    @DateTimeFormat
    private LocalDateTime createdAt;
    private String text;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post Post;

    @ManyToOne
    @JoinColumn(name = "idd")
    private User user;
}
