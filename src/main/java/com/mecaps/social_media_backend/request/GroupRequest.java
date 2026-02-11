package com.mecaps.social_media_backend.request;

import com.mecaps.social_media_backend.Enum.GroupType;
import com.mecaps.social_media_backend.Enum.JoinPolicy;
import com.mecaps.social_media_backend.Enum.PostPolicy;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;

@Getter
@Setter
public class GroupRequest {

    private String name;
    private String description;
    private GroupType groupType;
    private JoinPolicy joinPolicy;
    private PostPolicy postPolicy;


}
