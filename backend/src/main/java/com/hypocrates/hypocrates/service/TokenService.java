package com.hypocrates.hypocrates.service;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UUID userId) {
        return Jwts
                .builder()
                .header()
                .keyId(secret)
                .and()
                .claim("userId", userId)
                .compact();
    }
}
