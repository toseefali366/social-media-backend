package com.mecaps.social_media_backend.controller;


import com.mecaps.social_media_backend.request.GroupMemberRequest;
import com.mecaps.social_media_backend.response.GroupMemberResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.GroupMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groupMember")
@RequiredArgsConstructor

public class GroupMemberController {
    private final GroupMemberService groupMemberService;
@PostMapping("/addMember/{groupId}")
    public ResponseEntity<GroupMemberResponse> addMember(@PathVariable Long groupId,
                                                         @RequestBody GroupMemberRequest groupMemberRequest
                                                         , @CurrentUser CustomUserDetail currentUser){
        GroupMemberResponse response = groupMemberService.addMember(groupId,groupMemberRequest,currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
