package com.hypocrates.hypocrates.application.services.validator.UniqueStaffEmail;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueStaffEmailValidator.class)
public @interface UniqueStaffEmail {
    String message() default "Email already exists";

    Class[] groups() default {};

    Class[] payload() default {};
}
