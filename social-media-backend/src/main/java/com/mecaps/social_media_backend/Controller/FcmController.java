package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Entity.UserFcmToken;
import com.mecaps.social_media_backend.Repository.UserFcmTokenRepository;
import com.mecaps.social_media_backend.Request.FcmTokenRequest;
import com.mecaps.social_media_backend.Security.CurrentUser;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.ServiceImpl.FireBaseNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
public class FcmController {
    @Autowired
private UserFcmTokenRepository fcmTokenRepository;
    private final FireBaseNotificationService fireBaseNotificationService;

    public FcmController(FireBaseNotificationService fireBaseNotificationService) {
        this.fireBaseNotificationService = fireBaseNotificationService;
    }

    @PostMapping("/register")
public String registerToken( @CurrentUser CustomUserDetail user,
                                             @RequestBody FcmTokenRequest request){
        System.out.println("User Id from JWT = "+ user.getUser().getId());
        System.out.println("FCM Token from request = " + request.getToken());

        if (request.getToken() == null || request.getToken().isBlank()) {
            return "FCM token is missing!";
        }

        return fireBaseNotificationService.send(request.getToken());

    }
}
