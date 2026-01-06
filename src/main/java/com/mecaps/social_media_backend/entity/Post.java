package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.PostVisibility;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private PostVisibility postVisibility;
    @ManyToOne
    private User user;
    @Builder.Default
    private Boolean isDeleted = false;
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<PostContent> contents = new ArrayList<>();
}
