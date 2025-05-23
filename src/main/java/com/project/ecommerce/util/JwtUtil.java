package com.project.ecommerce.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {


    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 1일
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 역할 리스트도 받음
    public String generateToken(String email, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);  // roles 정보를 claims에 넣음

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 토큰에서 역할 리스트 추출 메서드 추가
    public List<String> getRolesFromToken(String token) {
        return (List<String>) Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }
}
