package com.mecaps.social_media_backend.Security;

import com.mecaps.social_media_backend.Entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
private final static long ACCESS_TOKEN_EXP = 1000*60*25;
private final static String SECRET_KEY = "fc69ab8b14b575976a71027d1a7cefc86ea9af8f4a1a7f828315125db2cc4bdb12b18db9";
private final static long REFRESH_TOKEN_EXP = 7 * 24 * 60 * 60 * 1000;

private SecretKey getSecretKey(){
return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
}

public String generateRefreshToken(User user){
return Jwts.builder().subject(user.getEmail()).claim("email",user.getEmail())
        .claim("type","RefreshToken").issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXP))
        .signWith(getSecretKey()).compact();
}

public String generateAccessToken(User user){
    return Jwts.builder().subject(user.getEmail()).claim("email",user.getEmail())
            .claim("type","AccessToken")
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXP))
            .signWith(getSecretKey())
            .compact();
}

public Claims extractAllclaim(String token){
    return Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getPayload();
}

    public LocalDateTime extractExpiry(String token) {
        return extractAllclaim(token)
                .getExpiration()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
public String extractEmail( String token){
return extractAllclaim(token).getSubject();
}

public boolean IstokenValid(String token){
try{
    return extractAllclaim(token).getExpiration().after(new Date());
}catch (Exception e){
    return false;
}
}

}

