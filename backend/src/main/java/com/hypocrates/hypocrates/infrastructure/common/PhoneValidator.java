package com.hypocrates.hypocrates.infrastructure.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hypocrates.hypocrates.infrastructure.config.database.clinics.repository.StaffRepository;

@RequiredArgsConstructor
public class PhoneValidator implements ConstraintValidator<Phone, String> {
  private final StaffRepository staffRepository;

  @Override
  public void initialize(Phone constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
    String phoneRegex = "+7\\d{10}";

    Pattern pattern = Pattern.compile(phoneRegex);
    Matcher matcher = pattern.matcher(phone);

    return matcher.find();
  }
}
