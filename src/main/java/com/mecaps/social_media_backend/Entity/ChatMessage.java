package com.mecaps.social_media_backend.Entity;

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
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatMessageId;

    private String content;
    @CreationTimestamp
    @DateTimeFormat
    private LocalDateTime timestamp;

    @ManyToOne
    private User receiver;

    @ManyToOne
    private User sender;

}
