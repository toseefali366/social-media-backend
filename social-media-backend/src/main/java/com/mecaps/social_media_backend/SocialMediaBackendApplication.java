package com.mecaps.social_media_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SocialMediaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaBackendApplication.class, args);
	}

}
