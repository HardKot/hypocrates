package com.hypocrates.hypocrates.interfaces;


import java.util.UUID;

public interface IJwtTokenBuilder {
    IJwtTokenBuilder payload(String key, String payload);

    IJwtTokenBuilder expiration(long minutes);

    IJwtTokenBuilder id(UUID id);

    String build();
}
