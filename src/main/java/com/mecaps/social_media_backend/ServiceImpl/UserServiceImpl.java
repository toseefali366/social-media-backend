package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Mapper.UserMapper;
import com.mecaps.social_media_backend.Repository.UserRepository;

import com.mecaps.social_media_backend.Request.ChangePasswordDTO;
import com.mecaps.social_media_backend.Request.UserRequest;

import com.mecaps.social_media_backend.Request.UpdateUserRequest;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.UserResponse;

import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;



    public ResponseEntity<?> createUser(UserRequest userRequest) {

        User user = userMapper.convertToUser(userRequest);

        // Upload images
        String profilePath = fileStorageService.saveFile(userRequest.getProfilePictureUrl(), "profile");
        String coverPath = fileStorageService.saveFile(userRequest.getCoverPictureUrl(), "cover");

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
        private void deleteImage(String imagePath){
        try {
            if (imagePath == null || imagePath.isBlank())
                return;

            String fullPath = System.getProperty("user.dir") + imagePath;
            File file = new File(fullPath);
            if (file.exists()) {
                boolean deleted = file.delete();
                if (!deleted) {
                    System.out.println("Failed to Delete File" + fullPath);
                }
            }
        }
            catch(Exception e){
                    e.printStackTrace();
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
         deleteImage(user.getProfilePictureUrl());
         deleteImage(user.getCoverPictureUrl());

        userRepository.delete(user);
    }

}


