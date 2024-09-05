package com.hypocrates.hypocrates.infrastructure.gateway;

import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.gateway.IAuthGateway;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.hypocrates.hypocrates.interfaces.IJwtServiceFacade;
import com.hypocrates.hypocrates.interfaces.IJwtTokenBuilder;
import com.hypocrates.hypocrates.interfaces.IKeyValueStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthGateway implements IAuthGateway {
    private final IStaffRepository staffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final IKeyValueStorage keyValueStorage;
    private final IJwtServiceFacade jwtServiceFacade;

    @Override
    public Staff findStaffByEmail(String email) {
        return staffRepository.findByEmail(email).orElse(null);
    }

    @Override
    public String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public IJwtTokenBuilder tokenBuilder() {
        return jwtServiceFacade.buildToken();
    }

    @Override
    public IArrayValueStorage getArrayValueStorage(String key) {
        return keyValueStorage.arrayValueStorage(key);
    }

    @Override
    public UUID extractUUIDFromToken(String token) {
        return jwtServiceFacade.extractUUID(token);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtServiceFacade.validateToken(token);
    }

    @Override
    public String extractUsernameFromToken(String token) {
        return jwtServiceFacade.extractUsername(token);
    }


}
