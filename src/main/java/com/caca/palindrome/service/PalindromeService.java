package com.caca.palindrome.service;

import com.caca.palindrome.repository.PalindromeRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class PalindromeService {

    private final PalindromeRepository palindromeRepository;

    public PalindromeService(PalindromeRepository palindromeRepository) {
        this.palindromeRepository = palindromeRepository;
    }

    public Set<String> findPalindromes(char[][] matrix) {
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
    }

    private Set<String> checkPalindrome(char[][] matrix, int row, int column, int rowIncrement, int columnIncrement) {
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

    //TODO perguntar se as palavras buscadas tem que ser maior que 4 letrás sempre?
    public boolean isPalindrome(String text) {
        boolean isPalindrome = false;
        if (StringUtils.hasText(text) && text.length() >= 4) {
            StringBuilder plain = new StringBuilder(text);
            StringBuilder reverse = plain.reverse();
            isPalindrome = (reverse.toString()).equals(text);
        }
        return isPalindrome;
    }
}

