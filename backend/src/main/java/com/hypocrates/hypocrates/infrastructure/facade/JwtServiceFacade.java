package com.hypocrates.hypocrates.infrastructure.facade;

import com.hypocrates.hypocrates.interfaces.IJwtServiceFacade;
import com.hypocrates.hypocrates.interfaces.IJwtTokenBuilder;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.ISecurityTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtServiceFacade implements IJwtServiceFacade {
    private final ISecurityTokenRepository tokenRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean validateToken(String token) {
        return validateToken(token, new Date());
    }

    public boolean validateToken(String token, Date date) {
        var tokenSchema = tokenRepository.findByToken(token);
        if (tokenSchema.isEmpty()) return false;

        return extractUsername(token) != null && extractExpiration(token).before(date);
    }

    @Override
    public String extractUsername(String token) {
        return extractPayload(token, "email");
    }

    @Override
    public String extractPayload(String token, String key) {
        return getParser().parseSignedClaims(token).getPayload().get(key).toString();
    }

    @Override
    public String extractTarget(String token) {
        return extractPayload(token, "target");
    }

    private Date extractExpiration(String token) {
        Date date = getParser().parseSignedClaims(token).getPayload().getExpiration();
        if (date == null) date = new Date();
        return date;
    }

    private SecretKey getSecretKey() {
        return Jwts.SIG.HS256.key().build();
    }

    private JwtParser getParser() {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build();
    }

    @Override
    public TokenBuilder buildToken() {
        return new TokenBuilder(secret);
    }

    @Override
    public UUID extractUUID(String token) {
        var id = getParser().parseSignedClaims(token).getPayload().getId();

        return UUID.fromString(id);
    }

    public static class TokenBuilder implements IJwtTokenBuilder {
        private final JwtBuilder jwtBuilder;

        private TokenBuilder(String secret) {
            Date expiration = Date.from(LocalDateTime.now().plusMinutes(20).toInstant(ZoneOffset.UTC));

            jwtBuilder = Jwts
                    .builder()
                    .header()
                    .and()
                    .issuedAt(new Date())
                    .expiration(expiration);
        }

        public TokenBuilder payload(String key, String value) {
            jwtBuilder
                    .claim(key, value);
            return this;
        }

        public TokenBuilder expiration(long expiration) {
            Date expirationDate = Date.from(LocalDateTime.now().plusMinutes(expiration).toInstant(ZoneOffset.UTC));

            jwtBuilder
                    .expiration(expirationDate);
            return this;
        }

        public TokenBuilder id(UUID id) {
            jwtBuilder
                    .header().keyId(id.toString());
            return this;
        }

        public String build() {
            return jwtBuilder.compact();
        }
    }
}
