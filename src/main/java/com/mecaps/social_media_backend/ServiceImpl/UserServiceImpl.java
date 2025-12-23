package com.mecaps.social_media_backend.ServiceImpl;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.UserNotFoundException;
import com.mecaps.social_media_backend.Mapper.UserMapper;
import com.mecaps.social_media_backend.Repository.UserRepository;
import com.mecaps.social_media_backend.Request.ChangePasswordDTO;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Response.UserResponse;
import com.mecaps.social_media_backend.Security.CustomUserDetail;
import com.mecaps.social_media_backend.Service.UserService;
import com.mecaps.social_media_backend.Validations.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Validation validation;
    private final PasswordEncoder passwordEncoder;


    public UserResponse createUser(UserRequest userRequest) {
        String userName = userRequest.getUserName();
        String email = userRequest.getEmail();
        String PhoneNumber = userRequest.getPhoneNumber();
        userRepository.findByEmailOrUserNameOrPhoneNumber(email, userName, PhoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User is Already Exist"));

        User user = userMapper.convertToUser(userRequest);

        String profilePath = validation.saveImage(
                userRequest.getProfilePictureUrl(), "profile");
        String coverPath = validation.saveImage(
                userRequest.getCoverPictureUrl(), "cover");

        user.setProfilePictureUrl(profilePath);
        user.setCoverPictureUrl(coverPath);

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }


    @Override
    public UserResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }


    @Override
    public UserResponse updateCurrentUser(
            CustomUserDetail currentUser,
            UserRequest request) {

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

        if (request.getUserName() != null)
            user.setUserName(request.getUserName());

        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());

        if (request.getLastName() != null)
            user.setLastName(request.getLastName());

        //  File uploads
        if (request.getProfilePictureUrl() != null && !request.getProfilePictureUrl().isEmpty()) {
            user.setProfilePictureUrl(
                    validation.saveImage(request.getProfilePictureUrl(), "profile")
            );
        }

        if (request.getCoverPictureUrl() != null && !request.getCoverPictureUrl().isEmpty()) {
            user.setCoverPictureUrl(
                    validation.saveImage(request.getCoverPictureUrl(), "cover")
            );
        }

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteCurrentUser(CustomUserDetail currentUser) {

        User user = currentUser.getUser();
        if (user == null) {
            throw new UserNotFoundException("Current user not found");
        }
        validation.deleteImage(user.getProfilePictureUrl());
        validation.deleteImage(user.getCoverPictureUrl());

        userRepository.delete(user);
    }


    public List<UserResponse> searchByUserName(String keyword) {

        // checking if keyword is empty

        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("Username cannot be Empty");
        }
        keyword = keyword.trim();

        // here's we calling database then this will return the result from database

        return userRepository
                .findByUserNameStartsWithIgnoreCase(keyword)
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public String updatePassword(CustomUserDetail customUserDetail, ChangePasswordDTO request) {
        User user = customUserDetail.getUser();
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("New password and confirm password must match!");
        }


        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return "Password has been changed successfully";
    }
}


