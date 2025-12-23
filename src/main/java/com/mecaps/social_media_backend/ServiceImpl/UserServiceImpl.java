package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Mapper.UserMapper;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FileStorageService fileStorageService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<?> createUser(UserRequest userRequest) {

        User user = userMapper.convertToUser(userRequest);

        // Upload images
        String profilePath = fileStorageService.saveFile(userRequest.getProfilePictureUrl(), "profile");
        String coverPath = fileStorageService.saveFile(userRequest.getCoverPictureUrl(), "cover");

        // Set path into entity
        user.setProfilePictureUrl(profilePath);
        user.setCoverPictureUrl(coverPath);

        // Save user
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User created successfully",
                "success", true
        ));
    }

}




