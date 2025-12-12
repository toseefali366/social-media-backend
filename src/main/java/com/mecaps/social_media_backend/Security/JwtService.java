package com.mecaps.social_media_backend.Security;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {
    private static final String SECRET_KEY = "23847560128376455892947630i8458845u84983457";

    public SecretKey getSecretkey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    }
}
