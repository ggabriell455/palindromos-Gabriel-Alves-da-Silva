package com.caca.palindrome.service;


import com.caca.palindrome.controller.PalindromeController;
import com.caca.palindrome.model.entity.Palindrome;
import com.caca.palindrome.model.exception.ResourceNotFoundException;
import com.caca.palindrome.repository.PalindromeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class PalindromeService {

    Logger logger = LoggerFactory.getLogger(PalindromeService.class);

    private final PalindromeRepository palindromeRepository;

    public PalindromeService(PalindromeRepository palindromeRepository) {
        this.palindromeRepository = palindromeRepository;
    }

    public Palindrome saveResults(Set<String> palindromes) {
        logger.info("Method: saveResults");
        return this.palindromeRepository.save(new Palindrome(palindromes));
    }

    public Palindrome findById(String id) {
        logger.info("Method: findById");
        return this.palindromeRepository.findById(UUID.fromString(id)).orElseThrow(ResourceNotFoundException::new);
    }
}
