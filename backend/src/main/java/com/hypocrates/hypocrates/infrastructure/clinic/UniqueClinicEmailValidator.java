package com.hypocrates.hypocrates.infrastructure.clinic;


import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ClinicSchemaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;



public class UniqueClinicEmailValidator implements ConstraintValidator<UniqueClinicEmail, String> {
    @Autowired
    private ClinicSchemaRepository clinicSchemaRepository;

    @Override
    public void initialize(UniqueClinicEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !clinicSchemaRepository.existsByEmail(s);
    }
}
