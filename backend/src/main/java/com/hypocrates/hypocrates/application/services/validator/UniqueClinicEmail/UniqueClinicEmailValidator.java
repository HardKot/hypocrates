package com.hypocrates.hypocrates.application.services.validator.UniqueClinicEmail;

import com.hypocrates.hypocrates.domain.adminManagement.interfaces.repository.IClinicRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueClinicEmailValidator implements ConstraintValidator<UniqueClinicEmail, String> {
    @Autowired
    private IClinicRepository clinicSchemaRepository;

    @Override
    public void initialize(UniqueClinicEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !clinicSchemaRepository.existsByEmail(s);
    }
}
