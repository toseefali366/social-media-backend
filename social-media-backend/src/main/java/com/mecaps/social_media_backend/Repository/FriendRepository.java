package com.mecaps.social_media_backend.Repository;

import com.mecaps.social_media_backend.Entity.Friend;
import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Enum.Status;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    @Query("""
        SELECT f FROM Friend f
        WHERE (f.sender.id = :u1 AND f.receiver.id = :u2)
           OR (f.sender.id = :u2 AND f.receiver.id = :u1)
    """)
    Optional<Friend> findBetween(Long u1, Long u2);



    List<Friend> findByReceiverIdAndStatus(Long receiverId, Status status);

    List<Friend> findBySenderOrReceiverAndStatus(
            User sender,
            User receiver,
            Status status
    );



    //    @Query("""
//        SELECT f FROM Friend f
//        WHERE f.receiver.id = :receiverId
//          AND f.status = :status
//    """)

//    List<Friend>findByReceiverAndStatus(Long Id, Status status);
    //    @Query("""
//        SELECT f FROM Friend f
//        WHERE (f.sender.id = :userId OR f.receiver.id = :userId)
//          AND f.status = :status
//    """)
//
//    List<Friend> findByUserAndStatus(User user, Status status);
}
