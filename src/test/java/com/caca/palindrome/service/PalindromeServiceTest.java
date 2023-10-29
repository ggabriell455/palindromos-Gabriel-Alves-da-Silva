package com.caca.palindrome.service;

import com.caca.palindrome.model.entity.Palindrome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PalindromeServiceTest {

    @Autowired
    private PalindromeService palindromeService;

    @Test
    @Sql({"/data/palindrome.sql"})
    void findById() {
        Palindrome palindrome = this.palindromeService.findById("c85c6752-4079-4fac-a063-2c7d301fb44a");

        assertNotNull(palindrome.getId());
        assertNotNull(palindrome.getCreatedAt());
        assertNotNull(palindrome.getResultList());

        Set<String> palindromes = palindrome.getResultList();
        assertEquals(4, palindromes.size());

        boolean containsAll = palindromes.containsAll(Arrays.asList("LPPL", "ARARA", "OSSO", "YJJY"));
        assertTrue(containsAll);
    }

    @Test
    @DisplayName("Should save data in database")
    void saveResults() {

        String word1 = "ARARA";
        String word2 = "BOOB";

        Set<String> results = new HashSet<>();
        results.add(word1);
        results.add(word2);

        Palindrome palindromeSaved = this.palindromeService.saveResults(results);
        assertNotNull(palindromeSaved.getId());
        assertNotNull(palindromeSaved.getCreatedAt());
        assertNotNull(palindromeSaved.getResultList());

        Palindrome palindromeFind = this.palindromeService.findById(palindromeSaved.getId().toString());
        Set<String> resultList = palindromeFind.getResultList();
        assertEquals(2, resultList.size());

        boolean containsAll = resultList.containsAll(Arrays.asList(word1, word2));
        assertTrue(containsAll);
    }
}