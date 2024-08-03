package com.hypocrates.hypocrates.service;

import com.hypocrates.hypocrates.database.admin.repository.TokenRepository;
import com.hypocrates.hypocrates.database.admin.schema.TokenSchema;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.UUID;

@Service
public class TokenService {
    private TokenRepository tokenRepository;

    TokenService(TokenRepository tokenRepository) {
        this.tokenRepository  = tokenRepository;
    }

    @Value("${jwt.secret}")
    private String secret;



    public String generateToken(UUID userId) {
        var token = Jwts
                .builder()
                .header()
                .keyId(secret)
                .and()
                .claim("userId", userId)
                .compact();

        tokenRepository.save(
                TokenSchema.builder()
                        .token(token)
                        .build());

        return token;
    }

    public UUID validateToken(String token) {
        var tokenSchema = tokenRepository.findByToken(token);
        if (tokenSchema.isEmpty()) return null;

        var jwk = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token);

        var payload = jwk.getPayload();

        return UUID.fromString(payload.get("userId").toString());
    }

    private SecretKey getSecretKey() {
        return Jwts.SIG.HS256.key().build();
    }
}
