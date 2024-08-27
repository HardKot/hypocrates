package com.hypocrates.hypocrates.application.services.UniqueStaffEmail;

import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueStaffEmailValidator implements ConstraintValidator<UniqueStaffEmail, String> {
    private final IStaffRepository staffRepository;

    @Override
    public void initialize(UniqueStaffEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !staffRepository.existsByEmail(s);
    }
}
