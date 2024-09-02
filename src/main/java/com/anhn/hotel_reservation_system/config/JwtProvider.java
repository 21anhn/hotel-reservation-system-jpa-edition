package com.anhn.hotel_reservation_system.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {

    public static final SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstraint.SECRECT_KEY.getBytes());

    public static String generateToken(String email) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 8640000))
                .claim("email", email)
                .signWith(secretKey)
                .compact();
    }

    public static String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody();
        return claims.get("email", String.class);
    }

}
