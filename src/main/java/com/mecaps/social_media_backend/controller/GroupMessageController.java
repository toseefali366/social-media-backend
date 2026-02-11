package com.mecaps.social_media_backend.controller;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.request.GroupMessageRequest;
import com.mecaps.social_media_backend.response.GroupMessageResponse;
import com.mecaps.social_media_backend.security.CurrentUser;
import com.mecaps.social_media_backend.security.CustomUserDetail;
import com.mecaps.social_media_backend.service.GroupMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GroupMessageController {

    private final GroupMessageService groupMessageService;

    @MessageMapping("/sendGroupMessage/{groupId}")
    public void sendGroupMessage(
            @DestinationVariable Long groupId,
            @Payload GroupMessageRequest groupMessageRequest,
            SimpMessageHeaderAccessor headerAccessor
    ) {

        if (groupId == null) {
            log.error("groupId is null");
            throw new IllegalArgumentException("groupId is null");
        }

        if (groupMessageRequest.getMessage() == null ||
                groupMessageRequest.getMessage().isBlank()) {
            log.error("message is null or empty");
            throw new IllegalArgumentException("message is null or empty");
        }

        //  GET USER FROM WEBSOCKET SESSION
        User currentUser = (User) headerAccessor
                .getSessionAttributes()
                .get("currentUser");

        if (currentUser == null) {
            throw new RuntimeException("Unauthenticated WebSocket user");
        }

        groupMessageService.sendGroupMessage(
                groupId,
                groupMessageRequest,
                currentUser
        );
    }

    @GetMapping("/groupMessage/{groupId}")
    public ResponseEntity<List<GroupMessageResponse>> getGroupMessages(
            @PathVariable Long groupId
    ) {
        List<GroupMessageResponse> messages = groupMessageService.getGroupMessages(groupId);
        return ResponseEntity.ok(messages);
    }

}

