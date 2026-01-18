package com.example.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.awt.*;
import java.util.Calendar;
import java.util.List;

/**
 * Cette classe va générer et vérifier un token
 */
public class JwtTokenManager {

    /**
     * Méthode qui permet de crypter token user
     * @param tokenUser
     * @return
     */
    public static String generateToken(String tokenUser, List<String> role) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        calendar.add(Calendar.DAY_OF_MONTH, 30);

        return Jwts.builder()
                .subject(tokenUser)
                .expiration(calendar.getTime())
                .signWith(secretKey(), Jwts.SIG.HS256)
                .compact();
    }


    public static String getUser(String token) throws Exception {
        Claims claims = Jwts.parser().verifyWith(secretKey()).build().parseSignedClaims(token).getPayload();

        return claims.getSubject();
    }

    private static SecretKey secretKey(){
        String cleCryptage = "wzUpGa9k4LTV3SHuY8qVrt6wOENkfdes5vLHVc1ex6581Iiq";
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(cleCryptage));
    }
    public static List<String> getRoles(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("roles", List.class);
    }

}
