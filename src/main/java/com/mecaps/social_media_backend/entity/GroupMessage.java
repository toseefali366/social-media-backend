package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @ManyToOne
    @JoinColumn(name ="groupId",nullable = false)
    private Group group;
    @ManyToOne
    @JoinColumn(name ="senderId",nullable = false)
    private User sender;
    @Column(nullable = false)
    private String message;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime sentAt;

}
