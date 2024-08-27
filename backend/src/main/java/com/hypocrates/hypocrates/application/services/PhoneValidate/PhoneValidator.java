package com.hypocrates.hypocrates.application.services.PhoneValidate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hypocrates.hypocrates.domain.clinicManagement.interfaces.repository.IStaffRepository;

@RequiredArgsConstructor
public class PhoneValidator implements ConstraintValidator<Phone, String> {
  private final IStaffRepository staffRepository;

  @Override
  public void initialize(Phone constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
    String phoneRegex = "\\+7\\d{10}";

    Pattern pattern = Pattern.compile(phoneRegex);
    Matcher matcher = pattern.matcher(phone);

    return matcher.find();
  }
}
