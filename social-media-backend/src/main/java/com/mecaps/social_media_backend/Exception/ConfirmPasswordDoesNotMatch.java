package com.mecaps.social_media_backend.Exception;

public class ConfirmPasswordDoesNotMatch extends RuntimeException {
    public ConfirmPasswordDoesNotMatch(String message) {
        super(message);
    }
}
