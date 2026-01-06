package com.mecaps.social_media_backend.request;

import com.mecaps.social_media_backend.Enum.ContentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostContentRequest {
    private String contentData;
    private Long position;
    private ContentType contentType;
    private MultipartFile file;
    private Long postId;
}
