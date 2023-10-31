package com.caca.palindrome.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PalindromeUtilsTest {

    @Test
    @DisplayName("Should be false the word ball is not a palindrome")
    void isPalindromeFalse() {
        String word = "bola";
        boolean palindrome = PalindromeUtils.isPalindrome(word);
        assertFalse(palindrome);
    }

    @Test
    @DisplayName("Should be true the word arara is a palindrome")
    void isPalindromeTrue() {
        String word = "arara";
        boolean palindrome = PalindromeUtils.isPalindrome(word);
        assertTrue(palindrome);
    }

    @Test
    @DisplayName("Should be false empty is not valid")
    void isPalindromeEmptyFalse() {
        String word = "";
        boolean palindrome = PalindromeUtils.isPalindrome(word);
        assertFalse(palindrome);
    }

    @Test
    @DisplayName("Should be false null is not valid")
    void isPalindromeNullFalse() {
        String word = null;
        boolean palindrome = PalindromeUtils.isPalindrome(word);
        assertFalse(palindrome);
    }

    @Test
    @DisplayName("Should be true for any character sequence that meets the palindrome rule")
    void isPalindromeAnySequence() {
        String textRandom = "1001";
        boolean isPalindrome = PalindromeUtils.isPalindrome(textRandom);

        assertTrue(isPalindrome);

        textRandom = "2rr2";
        isPalindrome = PalindromeUtils.isPalindrome(textRandom);
        assertTrue(isPalindrome);


        textRandom = "|4|4|";
        isPalindrome = PalindromeUtils.isPalindrome(textRandom);
        assertTrue(isPalindrome);

        textRandom = "/1-1/";
        isPalindrome = PalindromeUtils.isPalindrome(textRandom);
        assertTrue(isPalindrome);
    }


    @Test
    @DisplayName("Should be have 4 words")
    void findPalindromes() {

        char[][] matrix = {
                {'A', 'O', 'S', 'S', 'O'},
                {'Y', 'R', 'Z', 'X', 'L'},
                {'J', 'S', 'A', 'P', 'M'},
                {'J', 'K', 'P', 'R', 'Z'},
                {'Y', 'L', 'E', 'R', 'A'}
        };

        Set<String> palindromes = PalindromeUtils.findPalindromes(matrix);
        assertEquals(8, palindromes.size());

        boolean containsAll = palindromes.containsAll(Arrays.asList("LPPL", "ARARA", "OSSO", "YJJY", "ARA", "RAR", "ZPZ", "SZS"));
        assertTrue(containsAll);
    }
}