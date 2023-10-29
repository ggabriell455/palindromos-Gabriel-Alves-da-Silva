package com.caca.palindrome;

import com.caca.palindrome.controller.PalindromeController;
import com.caca.palindrome.repository.PalindromeRepository;
import com.caca.palindrome.service.PalindromeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class CacaPalindromeApplicationTests {

    @Autowired
    private PalindromeService palindromeService;

    @Autowired
    private PalindromeRepository palindromeRepository;

    @Autowired
    private PalindromeController palindromeController;

    @Test
    void contextLoads() {
        assertNotNull(palindromeService);
        assertNotNull(palindromeRepository);
        assertNotNull(palindromeController);
    }

}
