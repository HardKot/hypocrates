package com.hypocrates.hypocrates.application.services.UniqueStaffPhone;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueStaffPhoneValidator.class)
public @interface UniqueStaffPhone {
    String message() default "Phone already exists";

    Class[] groups() default {};

    Class[] payload() default {};
}
