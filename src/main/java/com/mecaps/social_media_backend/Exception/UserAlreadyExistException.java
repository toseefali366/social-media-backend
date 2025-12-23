package com.mecaps.social_media_backend.Exception;

public class UserAlreadyExistException extends RuntimeException{
  public UserAlreadyExistException(String message) {
    super(message);
  }
}
