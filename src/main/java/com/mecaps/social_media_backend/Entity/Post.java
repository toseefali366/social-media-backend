package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.Visiblity;
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
public class Post {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private Date createdAt;

private String text;

@Enumerated(EnumType.STRING)
private Visiblity visiblity;

@ManyToOne
@OnDelete(action = OnDeleteAction.CASCADE)

private User userId;

}
