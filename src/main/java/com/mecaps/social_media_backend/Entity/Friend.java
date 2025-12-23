package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friend {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Date createdAt;
@Enumerated(EnumType.STRING)
private Status status;

@ManyToOne
@OnDelete(action = OnDeleteAction.CASCADE)
private User userId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
private User userId2;
}
