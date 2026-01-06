package com.mecaps.social_media_backend.request;

import com.mecaps.social_media_backend.Enum.PostVisibility;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostRequest {
private String text;
private PostVisibility postVisibility;
private List<PostContentRequest> contents;
}
