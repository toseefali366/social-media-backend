package com.mecaps.social_media_backend.service;

import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;

import java.util.List;

public interface ProfileVisibilityService {
    boolean canViewProfile(User viewer, User profileOwner);
    List<Post> getVisiblePosts(User viewer, User profileOwner);
}
