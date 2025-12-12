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

//    private String saveImage(MultipartFile image , String folder) {
//        try{
//            if(image == null || image.isEmpty()) return null;
//
//            String uploadDir = "uploads/" + folder + "/";
//            File dir = new File(uploadDir);
//            if (!dir.exists()) dir.mkdirs();
//
//            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
//            String filePath = uploadDir + fileName;
//            image.transferTo(new File(filePath));
//            return filePath;
//        }catch (Exception e){
//            throw new RuntimeException("Failed to upload image");
//        }
//    }

//    private String saveImage(MultipartFile image, String folder) {
//
//        try {
//            if (image == null || image.isEmpty()) return null;
//
//            // Use absolute path
//            String uploadDir = System.getProperty("user.dir") + "/uploads/" + folder + "/";
//            File dir = new File(uploadDir);
//
//            if (!dir.exists() && !dir.mkdirs()) {
//                throw new RuntimeException("Failed to create directory: " + uploadDir);
//            }
//
//            // Avoid unsafe filenames
//            String original = image.getOriginalFilename().replaceAll("[^a-zA-Z0-9\\ .\\-]", "_");
//
//            String fileName = UUID.randomUUID() + "_" + original;
//            File destination = new File(uploadDir + fileName);
//
//            // Save file
//            image.transferTo(destination);
//
//            // Return relative path for frontend
//            return "/uploads/" + folder + "/" + fileName;
//
//        } catch (Exception e) {
//            e.printStackTrace(); // <-- So you can see exact root cause
//            throw new RuntimeException("Failed to upload image: " + e.getMessage());
//        }
//    }

}




