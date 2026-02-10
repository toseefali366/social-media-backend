package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Request.FriendRequestDTO;
import com.mecaps.social_media_backend.Request.FriendsActionDTO;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CurrentUser;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @PostMapping("/request")
    public ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestDTO request,@CurrentUser CustomUserDetail currentUser) {
        friendService.sendFriendRequest(request.getReceiverId(), currentUser.getUser());
        return ResponseEntity.ok("Friend Request Sent Succesfully");
    }

    @PostMapping("/accept")
    public ResponseEntity<?> acceptFriendRequest(@RequestBody FriendsActionDTO request, @CurrentUser CustomUserDetail currentUser) {
        friendService.acceptRequest(request.getRequestId(), currentUser.getUser());
        return ResponseEntity.ok("Friend Request Accepted");

    }

    @PostMapping("/reject")
    public ResponseEntity<String> rejectFriendRequest(
            @RequestBody FriendsActionDTO request,
            @CurrentUser CustomUserDetail currentUser) {

        friendService.rejectRequest(request.getRequestId(), currentUser.getUser());
        return ResponseEntity.ok("Friend request rejected");
    }

    @PostMapping("/friendlist")
    public ResponseEntity <List<UserResponse>>getFriends(
            @CurrentUser CustomUserDetail currentUser) {

        return ResponseEntity.ok(friendService.getFriends(currentUser.getUser()));
    }

    @PostMapping("/pendingrequests")
    public ResponseEntity<List<User>> getPendingRequests(
            @CurrentUser CustomUserDetail currentUser) {

        return ResponseEntity.ok(friendService.getPendingRequest(currentUser.getUser()));

    }
}
