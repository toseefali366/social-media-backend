package com.mecaps.social_media_backend.Service;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Response.UserResponse;

import java.util.List;

public interface FriendService{
    public void acceptRequest(Long requestID , User currentUser);
    void sendFriendRequest(Long receiverId , User currentUser);
    public void rejectRequest(Long requestId, User currentUser);
    public List<UserResponse> getFriends(User currentUser);
   // public List<User>getPendingRequest(User currentUser);
    public List<UserResponse>getPendingRequest(Long currentUserId);
}
