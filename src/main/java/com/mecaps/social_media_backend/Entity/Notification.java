package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.NotificationType;
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

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private String notificationContent;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @ManyToOne
    private User user;

    @CreationTimestamp
    @DateTimeFormat
    private LocalDateTime createdAt;

    @ManyToOne
    private Friend friendId;

    private Boolean isAccepted;
}
