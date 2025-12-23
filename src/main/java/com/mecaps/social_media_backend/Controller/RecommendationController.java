package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CurrentUser;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.ServiceImpl.RecommendationService;
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
