package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Mapper.UserMapper;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.UpdateUserRequest;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;



    public ResponseEntity<?> createUser(UserRequest userRequest) {

        User user = userMapper.convertToUser(userRequest);

        // Upload images
        String profilePath = saveImage(userRequest.getProfilePictureUrl(), "profile");
        String coverPath = saveImage(userRequest.getCoverPictureUrl(), "cover");

//         Set path into entity
        user.setProfilePictureUrl(profilePath);
       user.setCoverPictureUrl(coverPath);

        // Save user
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "User created successfully",
                "success", true
        ));
    }

    @Override
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

    private String saveImage(MultipartFile image, String folder) {

        try {
            if (image == null || image.isEmpty()) return null;

            // Use absolute path
            String uploadDir = System.getProperty("user.dir") + "/uploads/" + folder + "/";
            File dir = new File(uploadDir);

            if (!dir.exists() && !dir.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + uploadDir);
            }

            // Avoid unsafe filenames
            String original = image.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\ .\\-]", "_");

            String fileName = UUID.randomUUID() + "_" + original;
            File destination = new File(uploadDir + fileName);

            // Save file
            image.transferTo(destination);

            // Return relative path for frontend
            return "/uploads/" + folder + "/" + fileName;

        } catch (Exception e) {
            e.printStackTrace(); // <-- So you can see exact root cause
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }

        }
    @Override
    public UserResponse updateCurrentUser(
            CustomUserDetail currentUser,
            UpdateUserRequest request
    ) {


        User user = currentUser.getUser();

        if (request.getBio() != null)
            user.setBio(request.getBio());

        if (request.getLocation() != null)
            user.setLocation(request.getLocation());

        if (request.getPhoneNumber() != null)
            user.setPhoneNumber(request.getPhoneNumber());

        if (request.getPrivacySetting() != null)
            user.setPrivacySetting(request.getPrivacySetting());

        if (request.getGender() != null)
            user.setGender(request.getGender());

        if (request.getCountry() != null)
            user.setCountry(request.getCountry());

        if (request.getDob() != null)
            user.setDob(request.getDob());

        if(request.getUserName() != null)
            user.setUserName(request.getUserName());

        if(request.getFirstName() != null)
            user.setFirstName(request.getFirstName());

        if(request.getLastName() != null)
            user.setLastName(request.getLastName());

        //  File uploads
        if (request.getProfilePictureUrl() != null && !request.getProfilePictureUrl().isEmpty()) {
            user.setProfilePictureUrl(
                    saveImage(request.getProfilePictureUrl(), "profile")
            );
        }

        if (request.getCoverPictureUrl() != null && !request.getCoverPictureUrl().isEmpty()) {
            user.setCoverPictureUrl(
                    saveImage(request.getCoverPictureUrl(), "cover")
            );
        }

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteCurrentUser(CustomUserDetail currentUser) {

        User user = currentUser.getUser();
        if (user == null) {
            throw new RuntimeException("Current user not found");
        }

        userRepository.delete(user);
    }


}


