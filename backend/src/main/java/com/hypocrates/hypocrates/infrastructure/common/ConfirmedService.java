package com.hypocrates.hypocrates.infrastructure.common;

import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ConfirmedCodeRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.admin.schema.ConfirmedCodeSchema;
import com.hypocrates.hypocrates.infrastructure.config.exception.NotFoundSchema;
import com.hypocrates.hypocrates.infrastructure.libs.RandomLib;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmedService {
    private final ConfirmedCodeRepository confirmedCodeRepository;
    private final RandomLib randomLib;

    public boolean validateCode(UUID codeID, String code, LocalDateTime date) {
        var confirmedCodeResult = confirmedCodeRepository.findById(codeID);

        if (confirmedCodeResult.isEmpty()) {
            return false;
        }

        var confirmedCode = confirmedCodeResult.get();
        if (confirmedCode.getExpiration().isBefore(date)) {
           return false;
        }

        return confirmedCode.getCode().equals(code);
    }

    public boolean validateCode(UUID codeID, String code) {
        return validateCode(codeID, code, LocalDateTime.now());
    }

    public ConfirmedCodeSchema createConfirmedCode() {
        return confirmedCodeRepository.save(
                ConfirmedCodeSchema.builder()
                    .code(randomLib.randomCode())
                    .build()
        );
    }

    public boolean confirmCode(UUID codeId, String code) {
        if (!validateCode(codeId, code)) return false;
        confirmedCodeRepository.deleteById(codeId);
        return true;
    }


    public UUID getEntityId(UUID codeId, String code, LocalDateTime date) {
        var confirmedCode = confirmedCodeRepository.findById(codeId).orElseThrow(() -> new NotFoundSchema("Код подтверждения не найден"));
        if (confirmedCode.getCode().equals(code) && confirmedCode.getExpiration().isBefore(date) ) {
            return confirmedCode.getEntityID();
        }
        return null;
    }

    public UUID getEntityId(UUID codeId, String code) {
        return getEntityId(codeId, code, LocalDateTime.now());
    }
}