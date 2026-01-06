package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.Enum.FriendStatus;
import com.mecaps.social_media_backend.entity.Friend;
import com.mecaps.social_media_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query("""
    SELECT f FROM Friend f
    WHERE(f.sender = :u1 AND f.receiver = :u2)OR(f.sender= :u2 AND f.receiver = :u1)
    """)
    Optional<Friend> findBetween(User u1 , User u2);


    //Optional<Friend> findBetween(User user1, User user2);

    List<Friend> findByReceiverAndFriendStatus(User receiver, FriendStatus friendStatus);

    @Query("""
      SELECT f FROM Friend f WHERE(f.sender = :user OR f.receiver = :user)
      AND f.friendStatus = :friendStatus
      """)

    List<Friend> findByUserAndFriendStatus(User user, FriendStatus friendStatus);


    Optional<Friend> findBySenderAndReceiver(User sender, User receiver);

    Optional<Friend> findBySenderAndReceiverOrReceiverAndSender(
            User sender,
            User receiver,
            User receiver2,
            User sender2
    );
}
