package com.mecaps.social_media_backend.exception;

public class ConfirmPasswordDoesNotMatch extends RuntimeException {
    public ConfirmPasswordDoesNotMatch(String message) {
        super(message);
    }
}
