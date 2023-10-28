package com.caca.palindrome.model.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.MessageFormat;


public class MatrixSizeValidator implements ConstraintValidator<MatrixSizeValidation, char[][]> {

    private String message;

    private int rowMaxSize;

    private int columnMaxSize;

    private int rowMinSize;

    private int columnMinSize;

    @Override
    public void initialize(MatrixSizeValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.rowMaxSize = constraintAnnotation.rowMaxSize();
        this.columnMaxSize = constraintAnnotation.columnMaxSize();
        this.rowMinSize = constraintAnnotation.rowMinSize();
        this.columnMinSize = constraintAnnotation.columnMinSize();
    }

    @Override
    public boolean isValid(char[][] matrix, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();
        String message = MessageFormat.format(this.message, this.rowMaxSize, this.columnMaxSize, this.rowMinSize, this.columnMinSize);
        constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();

        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int rowSize = matrix.length;
        int columnSize = matrix[0].length;

        return rowSize >= this.rowMinSize && rowSize <= this.rowMaxSize &&
                columnSize >= this.columnMinSize && columnSize <= this.columnMaxSize;
    }
}
