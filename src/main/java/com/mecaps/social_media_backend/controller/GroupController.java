package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.request.GroupRequest;
import com.mecaps.social_media_backend.response.GroupResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/createGroup")
    public ResponseEntity<GroupResponse> createGroup(@RequestBody GroupRequest groupRequest, @CurrentUser CustomUserDetail customUserDetail) {

        return ResponseEntity.ok
                (groupService.createGroup(groupRequest, customUserDetail));

    }
}
