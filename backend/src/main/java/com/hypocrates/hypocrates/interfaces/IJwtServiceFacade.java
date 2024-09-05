package com.hypocrates.hypocrates.interfaces;


import java.util.UUID;

public interface IJwtServiceFacade {
    boolean validateToken(String token);

    String extractUsername(String token);

    IJwtTokenBuilder buildToken();

    UUID extractUUID(String token);

    String extractPayload(String token, String key);

    String extractTarget(String token);
}
