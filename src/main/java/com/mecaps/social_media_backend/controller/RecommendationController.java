package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.response.UserResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.serviceImpl.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;
    @GetMapping("/same-city")
    public ResponseEntity<List<UserResponse>>recommendUsers(@CurrentUser CustomUserDetail currentUser){
        User user = currentUser.getUser();
        List<UserResponse> user1 = recommendationService.recommendUsersByCity(user);
        return ResponseEntity.ok(user1);
    }
}
