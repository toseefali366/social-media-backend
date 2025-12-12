package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.Status;
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
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
}
