package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.FriendStatus;
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
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private FriendStatus status;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User sender;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User receiver;
}
