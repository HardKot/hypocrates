package com.hypocrates.hypocrates.application.services.UniqueClinicEmail;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueClinicEmailValidator.class)
public @interface UniqueClinicEmail {
    String message() default "Email already exists";

    Class[] groups() default {};

    Class[] payload() default {};
}
