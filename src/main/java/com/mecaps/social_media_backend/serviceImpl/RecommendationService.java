package com.mecaps.social_media_backend.serviceImpl;

import com.mecaps.social_media_backend.entity.Location;
import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.mapper.UserMapper;
import com.mecaps.social_media_backend.repository.LocationRepository;
import com.mecaps.social_media_backend.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final LocationRepository locationRepository;
    private final UserMapper userMapper;

    public List<UserResponse> recommendUsersByCity(User currentUser) {
        Location myLocation = locationRepository.findByUser(currentUser)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        if (myLocation.getCity() == null || myLocation.getCity().equalsIgnoreCase("unknown")) {
            return List.of();
        }

        List<Location> sameCityLocations = locationRepository.findByCityIgnoreCaseAndUserNot(
                myLocation.getCity(), currentUser
        );
        return sameCityLocations.stream()
                .map(Location::getUser)                 // Location → User
                .map(userMapper::toUserResponse)        // User → UserResponse
                .toList();
    }

    }

