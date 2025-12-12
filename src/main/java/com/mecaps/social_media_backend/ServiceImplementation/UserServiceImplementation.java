package com.mecaps.social_media_backend.ServiceImplementation;

import com.mecaps.social_media_backend.Entity.User;
import com.mecaps.social_media_backend.Exception.UserAlreadyExistsException;
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
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseEntity<?> userSignUP(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        if (userRequest.getPhoneNumber() != null &&
                !userRequest.getPhoneNumber().isEmpty()) {

            if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
                throw new UserAlreadyExistsException("Phone number already registered");
            }

        }
        if (userRepository.existsByUserName(userRequest.getUserName())) {
            throw new UserAlreadyExistsException("UserName is already registered");
        }


        User user = userMapper.convertToUser(userRequest);
        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "User has been signed up successfully",
                        "success", true
                ));

    }
}
