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
@AllArgsConstructor
@NoArgsConstructor
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @CreationTimestamp
    @DateTimeFormat
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user1;

    @ManyToOne
    private User user2;


}
