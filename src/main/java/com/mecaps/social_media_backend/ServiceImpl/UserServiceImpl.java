package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.UserAlreadyExistException;
import com.mecaps.social_media_backend.Mapper.UserMapper;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override

    public ResponseEntity<?> createUser(UserRequest request) {
        Optional<User> existingEmail = userRepository.findByEmail(request.getEmail());
        Optional<User> existingPhone = userRepository.findByPhoneNumber(request.getPhoneNumber());
        if(existingEmail.isPresent() && existingPhone.isPresent()){
            throw new UserAlreadyExistException
                    ("User With this Email or Phone Number Is Already Exist.");
        }
        User user = userMapper.convertToUser(request);
        String profilePath = saveImage(request.getProfilePicUrl(), "profile");

        // Set path into entity
        user.setProfilePicUrl(profilePath);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "User created successfully.",

                        "success", "true"
                ));
    }

    private String saveImage(MultipartFile image , String folder) {

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
}
