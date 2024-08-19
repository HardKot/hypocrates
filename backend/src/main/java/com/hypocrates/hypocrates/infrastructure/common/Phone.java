package com.hypocrates.hypocrates.infrastructure.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.swing.text.html.parser.Element;

import jakarta.validation.Constraint;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= PhoneValidator.class)
public @interface Phone {
  String message() default "Phone validate";

  Class[] groups() default {};

  Class[] payload() default {};
}
