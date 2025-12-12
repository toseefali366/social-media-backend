package com.mecaps.social_media_backend.Entity;

import com.mecaps.social_media_backend.Enum.ContentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postContentId;

    private String contentData;
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    private Long position;
    @ManyToOne
    private Post post;


}
