package com.mecaps.social_media_backend.repository;

import com.mecaps.social_media_backend.entity.Location;
import com.mecaps.social_media_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByUser(User user);
    List<Location> findByCityIgnoreCaseAndUserNot(String city, User user);
}
