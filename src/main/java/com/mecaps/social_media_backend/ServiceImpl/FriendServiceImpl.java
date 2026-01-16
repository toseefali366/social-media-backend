package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.Friend;
import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Enum.Status;
import com.mecaps.social_media_backend.Exception.NoFriendRequestFound;
import com.mecaps.social_media_backend.Exception.UserNotFoundException;
import com.mecaps.social_media_backend.Mapper.UserMapper;
import com.mecaps.social_media_backend.Repository.FriendRepository;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Service.FriendService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FriendServiceImpl implements FriendService {
    private final UserRepository userRepository;
private final FriendRepository friendRepository;
private final UserMapper userMapper;

public void acceptRequest(Long requestID , User currentUser){
    Friend friend = friendRepository.findById(requestID)
            .orElseThrow(()->new NoFriendRequestFound("REQUEST NOT FOUND"));


    if(friend.getStatus()!=Status.PENDING){
        throw  new NoFriendRequestFound("Request is not Pending");
    }
friend.setStatus(Status.ACCEPTED);
  log.info("friend request accepted by {} ", currentUser.getId());
  friendRepository.save(friend);


}

    public void sendFriendRequest(Long receiverId , User currentUser){
        if(currentUser.getId().equals(receiverId)){
            throw new UserNotFoundException( "You can't Send Friend Request To Yourself");
        }

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(()->new UsernameNotFoundException("USer not Found"));

        Optional<Friend> existingFriend = friendRepository.findBetween(receiverId,receiver.getId());
        if(existingFriend.isPresent()){
            throw new RuntimeException("Friend Request Already been Sent");
        }

        Friend friend = Friend.builder()
                .sender(currentUser)
                .receiver(receiver)
                .status(Status.PENDING)
                .build();

        friendRepository.save(friend);

    }

public void rejectRequest(Long requestId, User currentUser){
    Friend friend = friendRepository.findById(requestId)
            .orElseThrow(()->new NoFriendRequestFound("Friend Request Not found"));

    friend.setStatus(Status.REJECTED);
    friendRepository.save(friend);
}

public List<UserResponse> getFriends(User currentUser) {
    List<Friend> friendList = friendRepository.findByUserAndStatus(currentUser, Status.ACCEPTED);

    List<UserResponse> friends = new ArrayList<>();
for ( Friend f : friendList){

        User friendUser =  f.getSender().equals(currentUser)
         ? f.getReceiver(): f.getSender();
        friends.add(userMapper.toUserResponse(friendUser));

}
    return friends;
}

@Transactional
public List<User>getPendingRequest(User currentUser){
     return friendRepository.findByReceiverAndStatus(currentUser.getId(),Status.PENDING)
             .stream()
             .map(Friend::getSender)
             .toList();
}

}
