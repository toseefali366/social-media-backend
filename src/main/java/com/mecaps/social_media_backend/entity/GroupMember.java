package com.mecaps.social_media_backend.entity;

import com.mecaps.social_media_backend.Enum.Role;
import com.mecaps.social_media_backend.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(
        name = "groupMembers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userId", "groupId"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @CreationTimestamp
    private LocalDateTime joinedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Role role;
}
