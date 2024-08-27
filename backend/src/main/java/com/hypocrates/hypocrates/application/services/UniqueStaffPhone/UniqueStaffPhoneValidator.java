package com.hypocrates.hypocrates.application.services.UniqueStaffPhone;

import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueStaffPhoneValidator implements ConstraintValidator<UniqueStaffPhone, String> {
    private final IStaffRepository staffRepository;

    @Override
    public void initialize(UniqueStaffPhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !staffRepository.existsByPhone(s);
    }
}
