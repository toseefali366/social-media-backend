package com.mecaps.social_media_backend.ServiceImpl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.mecaps.social_media_backend.Entity.UserFcmToken;
import com.mecaps.social_media_backend.Repository.UserFcmTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireBaseNotificationService {

    @Autowired
    private final UserFcmTokenRepository userFcmTokenRepository;

    public FireBaseNotificationService(UserFcmTokenRepository userFcmTokenRepository) {
        this.userFcmTokenRepository = userFcmTokenRepository;
    }

    public void sendToUser(Long userId , String title , String body){
        List<UserFcmToken> tokens = userFcmTokenRepository.findByUserId(userId);
for (UserFcmToken t : tokens){
    send(t.getToken(),title,body);
}

    }


    public String send(String token, String title , String body){
        System.out.println("FCM TOKEN RECEIVED = " + token);

//        Message message = Message.builder().setToken(token).setNotification(Notification.builder()
//                .setTitle("Test Notification").setBody("FireBase works with JWT").build())
//                .build();

        try{
            Message message = Message.builder().setToken(token).setNotification(Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build())
                    .build();

            return FirebaseMessaging.getInstance().send(message);
        }catch (Exception e){
            e.printStackTrace();

            return e.getMessage();
        }
    }

}
