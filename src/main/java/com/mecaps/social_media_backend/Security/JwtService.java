package com.mecaps.social_media_backend.Security;

import com.mecaps.social_media_backend.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
private static final long EXPIRATION_TIME = 1000*60*60;
private static final String SECRET_KEY = "V9sPq2Xh7LmC8rT4bN6qWz9Kf3YpR2tD";
private SecretKey getSecretKey(){
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
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


}
