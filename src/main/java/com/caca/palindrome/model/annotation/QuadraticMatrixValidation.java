package com.caca.palindrome.model.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = QuadraticMatrixValidator.class)
public @interface QuadraticMatrixValidation {
    String message() default "A matriz não é quadrada";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
