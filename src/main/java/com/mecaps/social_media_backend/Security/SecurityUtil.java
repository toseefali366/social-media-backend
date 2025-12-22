package com.mecaps.social_media_backend.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    private SecurityUtil(){}

    public static CustomUserDetail getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }

        return (auth.getPrincipal() instanceof CustomUserDetail user)
                ? user
                : null;
    }


}
