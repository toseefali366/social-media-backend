package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.Enum.AccountStatus;
import com.mecaps.social_media_backend.Enum.FriendStatus;
import com.mecaps.social_media_backend.Enum.ProfilePrivacy;
import com.mecaps.social_media_backend.entity.Friend;
import com.mecaps.social_media_backend.entity.Post;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.repository.FriendRepository;
import com.mecaps.social_media_backend.repository.PostRepository;
import com.mecaps.social_media_backend.service.ProfileVisibilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileVisibilityServiceImpl implements ProfileVisibilityService {
    private final FriendRepository friendRepository;
    private final PostRepository postRepository;

    @Override
    public boolean canViewProfile(User viewer, User profileOwner){
        //  Same user
        if(viewer.getId().equals(profileOwner.getId())){
            return true;
        }

        //  Account status check
        if(profileOwner.getAccountStatus() != AccountStatus.ACTIVE){
            return false;
        }

        // 3. Block check
        Friend relation = getRelation(viewer,profileOwner);
        if(relation != null && relation.getFriendStatus() == FriendStatus.BLOCKED){
            return false;
        }

        return true;
    }

    @Override
    public List<Post> getVisiblePosts(User viewer, User profileOwner){
        // Same user  everything
        if(viewer.getId().equals(profileOwner.getId())){
            log.info("user fetching posts is same as current user, fetching all posts" );
            return  postRepository.findByUserAndIsDeletedFalse(profileOwner);
        }

        // Account inactive
        if(profileOwner.getAccountStatus() != AccountStatus.ACTIVE){
            log.info("profile owner is not active, so we return empty post list");
            return List.of();
        }

        // Blocked
        Friend relation = getRelation(viewer,profileOwner);
        if(relation != null && relation.getFriendStatus() == FriendStatus.BLOCKED){
            log.info("profile owner blocked the user, So we return empty post list");
            return List.of();
        }

        // PRIVATE profile & not friend
        boolean isFriend = relation !=null  && relation.getFriendStatus() == FriendStatus.ACCEPTED;
        if(profileOwner.getProfilePrivacy() == ProfilePrivacy.PRIVATE && !isFriend){
            log.info("profile owner account is private and the user is not a friend, so we return empty post list");
            return List.of();
        }
        return postRepository.findByUserAndIsDeletedFalse(profileOwner)
                .stream()
                .filter(post -> canViewPost(viewer, post, isFriend))
                .toList();
    }

    private boolean canViewPost(User viewer ,Post post, boolean isFriend){
       switch(post.getPostVisibility()){
           case PUBLIC:
               return true;

           case FRIENDS_ONLY:
               return isFriend;

           case ONLY_ME:
               return viewer.getId().equals(post.getUser().getId());

           default:
               return false;
       }
    }

    private Friend getRelation(User viewer, User profileOwner){
        return friendRepository
                .findBySenderAndReceiverOrReceiverAndSender(
                        viewer,profileOwner,
                        viewer,profileOwner)
                .orElse(null);
    }
}
