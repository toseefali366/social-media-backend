package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.Enum.Role;
import com.mecaps.social_media_backend.entity.Group;
import com.mecaps.social_media_backend.entity.GroupMember;
import com.mecaps.social_media_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember,Long> {
    Boolean existsByGroupAndUser(Group group, User user);

   // Optional<GroupMember> findByGroupAndUser(Group group,User user);
    Optional<GroupMember> findByGroupIdAndUserIdAndRole(Long groupId, Long userId, Role role);
}
