package com.hypocrates.hypocrates.infrastructure.staff;


import com.hypocrates.hypocrates.infrastructure.config.database.admin.repository.ClinicSchemaRepository;
import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.StaffRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;



@RequiredArgsConstructor
public class UniqueStaffEmailValidator implements ConstraintValidator<UniqueStaffEmail, String> {
    private final  StaffRepository staffRepository;

    @Override
    public void initialize(UniqueStaffEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !staffRepository.existsByEmail(s);
    }
}
