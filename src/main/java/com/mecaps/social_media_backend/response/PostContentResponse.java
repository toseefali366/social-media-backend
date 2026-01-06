package com.mecaps.social_media_backend.response;

import com.mecaps.social_media_backend.Enum.ContentType;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PostContentResponse {
    private Long id;
    private ContentType contentType;
    private String contentData;
    private Long position;
}
