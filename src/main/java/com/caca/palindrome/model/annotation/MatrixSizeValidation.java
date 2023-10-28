package com.caca.palindrome.model.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatrixSizeValidator.class)
public @interface MatrixSizeValidation {
    String message() default "Tamanho da matrix é inválido. As dimensões máximas são {0}x{1} e minimas são {2}x{3}";

    int rowMaxSize();

    int columnMaxSize();

    int rowMinSize() default 1;

    int columnMinSize() default 1;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
