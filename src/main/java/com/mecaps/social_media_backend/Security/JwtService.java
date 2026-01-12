package com.mecaps.social_media_backend.Security;

import com.mecaps.social_media_backend.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
private static final long EXPIRATION_TIME = 1000*60*60;
    @Value("${jwt.secret}")
    private String jwtSecret;
    private static final long REFRESH_TOKEN_EXP = 7 * 24 * 60 * 60 * 1000;

private SecretKey getSecretKey(){
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
};

public String generateAccessToken(User user){
    return Jwts.builder()
            .subject(user.getEmail())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSecretKey())
            .compact();
}
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

       public String extractEmail(String token){
            return extractAllClaims(token).getSubject();
}
       public boolean isTokenValid(String token){
           Claims claims = extractAllClaims(token);
           return claims.getExpiration().after(new Date());
}

    public String generateRefreshToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("email", user.getEmail())
                .claim("type","RefreshToken")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXP))
                .signWith(getSecretKey()).compact();
    }

    public LocalDateTime extractExpiry(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
