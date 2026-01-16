package com.mecaps.social_media_backend.Repository;

import com.mecaps.social_media_backend.Entity.Location;
import com.mecaps.social_media_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByUser(User user);
    List<Location> findByCityIgnoreCaseAndUserNot(String city, User user);
}
