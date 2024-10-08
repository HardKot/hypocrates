package com.hypocrates.hypocrates.application.services.validator.PhoneValidate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= PhoneValidator.class)
public @interface Phone {
  String message() default "Phone validate";

  Class[] groups() default {};

  Class[] payload() default {};
}
