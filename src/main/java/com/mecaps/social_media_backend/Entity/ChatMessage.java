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
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private LocalDateTime timeStamp;
@ManyToOne
@OnDelete(action = OnDeleteAction.CASCADE)

    private User receiverId;
@ManyToOne
@OnDelete(action = OnDeleteAction.CASCADE)

    private User senderId;
}
