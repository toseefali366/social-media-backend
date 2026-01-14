package com.mecaps.social_media_backend.Exception;

public class NoFriendRequestFound extends RuntimeException {
  public NoFriendRequestFound(String message) {
    super(message);
  }
}
