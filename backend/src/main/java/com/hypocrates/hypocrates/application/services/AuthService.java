package com.hypocrates.hypocrates.application.services;

import com.hypocrates.hypocrates.application.dto.TokenDTO;
import com.hypocrates.hypocrates.application.exception.AuthServiceException;
import com.hypocrates.hypocrates.domain.clinicManagement.entities.Staff;
import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.gateway.IAuthGateway;
import com.hypocrates.hypocrates.interfaces.IArrayValueStorage;
import com.leakyabstractions.result.api.Result;
import com.leakyabstractions.result.core.Results;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.validation.annotation.Validated;

import java.util.UUID;


@Slf4j
@Service
@Validated
public class AuthService {
    private final IAuthGateway authGateway;
    private final IArrayValueStorage refreshTokenStorage;

    public AuthService(IAuthGateway authGateway) {
        this.authGateway = authGateway;
        this.refreshTokenStorage = authGateway.getArrayValueStorage("refreshToken");
    }

    public Result<TokenDTO, AuthServiceException> loginStaff(String email, String password) {
        Staff staff = authGateway.findStaffByEmail(email);

        if (staff == null || !staff.getPassword().equals(authGateway.encryptPassword(password))) {
            return Results.failure(new AuthServiceException("Incorrect password"));
        }

        if (staff.isBanned()) return Results.failure(new AuthServiceException("Banned"));
        UUID refreshTokenID = UUID.randomUUID();

        String refreshToken = authGateway.tokenBuilder()
                .payload("target", "refreshToken")
                .payload("email", staff.getEmail())
//                .payload("clinicCode", staff.getClinic().getCode())
                .id(refreshTokenID)
                .expiration(28 * 24 * 60 )
                .build();

        String token = authGateway.tokenBuilder()
                .payload("target", "auth")
                .payload("email", staff.getEmail())
//                .payload("clinicCode", staff.getClinic().getCode())
                .payload("refreshTokenId", refreshTokenID.toString())
                .expiration(30)
                .build();

        refreshTokenStorage.add(refreshTokenID);
        return Results.success(new TokenDTO(token, refreshToken));
    }

    public Result<TokenDTO, AuthServiceException> refreshJWT(String refreshToken) {
        UUID tokenID = authGateway.extractUUIDFromToken(refreshToken);
        if (tokenID == null || !refreshTokenStorage.has(tokenID)) {
            return Results.failure(new AuthServiceException("Invalid refresh token"));
        }

        String staffEmail = authGateway.extractUsernameFromToken(refreshToken);

        if (staffEmail == null || staffEmail.isEmpty()) {
            return Results.failure(new AuthServiceException("Invalid refresh token"));
        }

        Staff staff = authGateway.findStaffByEmail(staffEmail);

        if (staff == null) {
            return Results.failure(new AuthServiceException("Invalid refresh token"));
        }


       String token = authGateway.tokenBuilder()
                .payload("target", "auth")
                .payload("email", staff.getEmail())
//                .payload("clinicCode", staff.getClinic().getCode())
                .payload("refreshTokenId", tokenID.toString())
                .expiration(30)
                .build();
        refreshTokenStorage.add(token);
        return Results.success(new TokenDTO(token, refreshToken));
    }

    public Result<Boolean, AuthServiceException> logout(String token) {
        UUID tokenID = authGateway.extractUUIDFromToken(token);
        refreshTokenStorage.delete(tokenID);

        return Results.success(true);
    }
}
