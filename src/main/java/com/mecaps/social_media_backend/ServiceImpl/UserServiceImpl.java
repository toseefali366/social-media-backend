package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Mapper.UserMapper;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.ChangePasswordDTO;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final FileStorageService fileStorageService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

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

    public ResponseEntity<?> searchByUserName(String keyword) {

        // checking if keyword is empty

        if (keyword == null || keyword.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                            "message", "Username cannot be empty",
                            "success", false
                    )
            );
        }
        keyword = keyword.trim();

            // here's we calling database then this will return the result from database

        return ResponseEntity.ok(
                userRepository.findByUserNameStartsWithIgnoreCase(keyword)
        );

    }

    public String updatePassword(CustomUserDetail customUserDetail, ChangePasswordDTO request){
        User user = customUserDetail.getUser();
        if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            throw new RuntimeException("Incorrect password" );
        }

        if(!request.getNewPassword().equals(request.getConfirmPassword())){
            throw new RuntimeException("New password and confirm password must match!" );
        }

    user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return "Password has been changed successfully";
    }

}




