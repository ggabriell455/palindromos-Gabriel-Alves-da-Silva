package com.caca.palindrome.model.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class QuadraticMatrixValidator implements ConstraintValidator<QuadraticMatrixValidation, char[][]> {
    @Override
    public void initialize(QuadraticMatrixValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(char[][] matrix, ConstraintValidatorContext constraintValidatorContext) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int n = matrix.length;
        for (char[] row : matrix) {
            if (row.length != n) {
                return false;
            }
        }
        return true;
    }
}
