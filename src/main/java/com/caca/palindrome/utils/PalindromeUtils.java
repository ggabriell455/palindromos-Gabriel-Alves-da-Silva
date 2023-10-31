package com.caca.palindrome.utils;

import com.caca.palindrome.model.exception.UnableSearchPalindromeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class PalindromeUtils {

    static Logger logger = LoggerFactory.getLogger(PalindromeUtils.class);

    public static Set<String> findPalindromes(char[][] matrix) {
        logger.info("Method: findPalindromes");

        try {
            int rows = matrix.length;
            int columns = matrix[0].length;
            Set<String> palindromes = new HashSet<>();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // Find horizontal palindromes
                    palindromes.addAll(checkPalindrome(matrix, i, j, 0, 1));

                    // Find vertical palindromes
                    palindromes.addAll(checkPalindrome(matrix, i, j, 1, 0));

                    // Find diagonal palindromes (left to right)
                    palindromes.addAll(checkPalindrome(matrix, i, j, 1, 1));

                    // Find diagonal palindromes (right to left)
                    palindromes.addAll(checkPalindrome(matrix, i, j, 1, -1));
                }
            }

            return palindromes;
        } catch (Exception e) {
            logger.error("Unable to perform search", e.getCause());
            throw new UnableSearchPalindromeException("The search could not be performed, please check the data and try again.");
        }
    }

    // Metodo auxiliar pra percorrer repetidas vezes a partir de cada nova posição em qualquer direção.
    // rowIncrement e columnIncrement são os responsaveis pela forma que o indice é incrementado e com isso a forma que se movimenta na matrix

    private static Set<String> checkPalindrome(char[][] matrix, int row, int column, int rowIncrement, int columnIncrement) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        StringBuilder word = new StringBuilder();
        Set<String> palindromes = new HashSet<>();

        while (row >= 0 && row < rows && column >= 0 && column < cols) {
            word.append(matrix[row][column]);

            if (isPalindrome(word.toString())) {
                palindromes.add(word.toString());
            }

            row += rowIncrement;
            column += columnIncrement;
        }

        return palindromes;
    }

    //Assumindo que só são palidromos os agrupamentos que tiverem mais de 3 caracteres
    public static boolean isPalindrome(String text) {
        logger.info("Method: isPalindrome");

        boolean isPalindrome = false;
        if (StringUtils.hasText(text) && text.length() >= 3) {
            StringBuilder plain = new StringBuilder(text);
            StringBuilder reverse = plain.reverse();
            isPalindrome = (reverse.toString()).equals(text);
        }
        return isPalindrome;
    }
}

