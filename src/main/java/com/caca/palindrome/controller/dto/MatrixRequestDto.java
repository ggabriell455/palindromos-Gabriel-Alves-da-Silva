package com.caca.palindrome.controller.dto;

import com.caca.palindrome.model.annotation.MatrixSizeValidation;
import com.caca.palindrome.model.annotation.QuadraticMatrixValidation;

public class MatrixRequestDto {

    @QuadraticMatrixValidation
    @MatrixSizeValidation(rowMaxSize = 10, columnMaxSize = 10)
    private char[][] matrix;

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

}