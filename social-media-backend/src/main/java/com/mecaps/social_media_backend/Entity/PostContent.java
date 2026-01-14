package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.ContentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentData;
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    private Long position;
    @ManyToOne
    private Post post;
}
