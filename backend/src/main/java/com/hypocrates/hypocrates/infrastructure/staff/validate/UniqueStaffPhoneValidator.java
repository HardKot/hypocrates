package com.hypocrates.hypocrates.infrastructure.staff.validate;

import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ClinicSchemaRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.StaffRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class UniqueStaffPhoneValidator implements ConstraintValidator<UniqueStaffPhone, String> {
    private final StaffRepository staffRepository;

    @Override
    public void initialize(UniqueStaffPhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !staffRepository.existsByPhone(s);
    }
}
