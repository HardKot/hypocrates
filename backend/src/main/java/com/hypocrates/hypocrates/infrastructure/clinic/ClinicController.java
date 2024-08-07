package com.hypocrates.hypocrates.infrastructure.clinic;

import com.hypocrates.hypocrates.infrastructure.clinic.dto.RegistrationClinicForm;
import com.hypocrates.hypocrates.useCase.InteractError;
import com.hypocrates.hypocrates.useCase.clinicInteract.ClinicInteract;

import com.hypocrates.hypocrates.useCase.clinicInteract.dto.ClinicRegistrationResult;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@Controller
@RequestMapping("/clinic")
@AllArgsConstructor
public class ClinicController {
    private ClinicInteract clinicInteract;

    @PostMapping("/")
    public ResponseEntity<ClinicRegistrationResult> createClinic(
            @RequestBody
            @Valid RegistrationClinicForm form
            ) throws InteractError {
        var result = clinicInteract.registrationClinic(form);

        if (result.hasSuccess()) {
            return ResponseEntity.ok().body(result.getSuccess().orElse(new ClinicRegistrationResult(null, null)));
        }
        throw result.getFailure().orElse(new InteractError("Unknown error"));
    }

}

