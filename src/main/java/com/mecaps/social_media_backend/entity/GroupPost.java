package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class GroupPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User user;
    @ManyToOne
    private Group group;
    @ManyToOne
    private User approvedBy;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime approvedAt;
    @Enumerated(EnumType.STRING)
    private Status status;

}
