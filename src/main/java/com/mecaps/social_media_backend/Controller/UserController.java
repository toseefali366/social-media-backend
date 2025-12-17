package com.mecaps.social_media_backend.Controller;

import com.mecaps.social_media_backend.Request.ChangePasswordDTO;
import com.mecaps.social_media_backend.Request.UserRequest;
import com.mecaps.social_media_backend.Service.UserService;
import com.mecaps.social_media_backend.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserServiceImpl userServiceImple;
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createUser(@ModelAttribute UserRequest userRequest) {

    return userService.createUser(userRequest);
}

@GetMapping("/search")
    public ResponseEntity<?> searchByUserName(@RequestParam String keyword){
        return userService.searchByUserName(keyword);

}

@PutMapping("/changePassword/{id}")
    public String setPassword(@PathVariable Long id, @RequestBody ChangePasswordDTO request){
        return userServiceImple.setPassword(id,request);
}

}
