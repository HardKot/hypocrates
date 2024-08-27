package com.hypocrates.hypocrates.infrastructure.configs.security;

import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.ISecurityTokenRepository;
import com.hypocrates.hypocrates.configs.database.clinics.schema.StaffSchema;
import com.hypocrates.hypocrates.configs.database.clinics.schema.TokenSchema;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final ISecurityTokenRepository tokenRepository;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(StaffSchema staff) {
        var token = Jwts
                .builder()
                .header()
                .keyId(secret)
                .and()
                .claim("email", staff.getEmail())
                .claim("userId", staff.getId())
                .claim("role", staff.getRole())
                .compact();

        tokenRepository.save(
                TokenSchema.builder()
                        .token(token)
                        .build());

        return token;
    }

    public boolean validateToken(String token) {
        return validateToken(token, new Date());
    }

    public boolean validateToken(String token, Date date) {
        var tokenSchema = tokenRepository.findByToken(token);
        if (tokenSchema.isEmpty()) return false;

        var jwk = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token);

        return jwk.getPayload().get("email") != null && jwk.getPayload().getExpiration().before(date);
    }

    public String extractUsername(String token) {
        var jwk = Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token);

        return jwk.getPayload().get("email").toString();
    }

    private SecretKey getSecretKey() {
        return Jwts.SIG.HS256.key().build();
    }
}
