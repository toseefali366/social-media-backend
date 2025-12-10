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

    private String saveImage(MultipartFile image , String folder) {
        try{
            if(image == null || image.isEmpty()) return null;

            String uploadDir = "uploads/" + folder + "/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            String filePath = uploadDir + fileName;
            image.transferTo(new File(filePath));
            return filePath;
        }catch (Exception e){
            throw new RuntimeException("Failed to upload image");
        }
    }
}




