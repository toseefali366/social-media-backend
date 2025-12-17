package com.mecaps.social_media_backend.Repository;

import com.mecaps.social_media_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

Optional<User> findByEmailOrUserNameOrPhoneNumber(String email, String userName, String phoneNumber);

List<User> findByUserNameStartsWithIgnoreCase(String userName);
}


