package com.mecaps.social_media_backend.config;

import com.mecaps.social_media_backend.entity.User;
import com.mecaps.social_media_backend.repository.UserRepository;
import com.mecaps.social_media_backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) {

        try {
            String authHeader = request.getHeaders().getFirst("authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("Missing Authorization header");
                return false;
            }

            String token = authHeader.substring(7);

            if (!jwtService.isTokenValid(token)) {
                System.out.println("Invalid JWT");
                return false;
            }

            String email = jwtService.extractEmail(token);

            User user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                System.out.println("User not found: " + email);
                return false;
            }

            attributes.put("currentUser", user);

            System.out.println("WebSocket authenticated for " + email);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
    }
}