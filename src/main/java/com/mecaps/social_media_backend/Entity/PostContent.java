package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.ContentType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentData;

    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private Integer position;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)

    private Post postId;

}
