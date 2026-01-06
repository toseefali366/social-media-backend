package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.NotificationType;
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
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String notificationContent;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Friend friend;
    private boolean isAccepted;
    }

