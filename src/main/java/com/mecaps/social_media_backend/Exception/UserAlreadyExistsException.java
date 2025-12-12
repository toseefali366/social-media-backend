package com.mecaps.social_media_backend.Exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserAlreadyExistsException extends RuntimeException {

        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }



