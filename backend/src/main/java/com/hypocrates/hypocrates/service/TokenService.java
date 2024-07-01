package com.hypocrates.hypocrates.service;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Long userId) {
        var jwt = Jwts
                .builder()
                .header()
                .keyId(secret)
                .and()
                .claim("userId", userId)
                .compact();
        return null;
    }
}
