package com.mecaps.social_media_backend.Config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;


import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
@Configuration
public class FireBaseConfig {
    static {
        System.out.println("🔥 FireBaseConfig class loaded");
    }
    @PostConstruct
    public void initialize() {
        System.out.println("🔥 FireBaseConfig initialize() called");

        try {
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");
            if (serviceAccount == null) {
                throw new RuntimeException("Firebase-service-account.json not found in classpath");
            }

            FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("Firebase Initia lized");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize Firebase");
        }
    }
}
