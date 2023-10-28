package com.caca.palindrome.service;


import com.caca.palindrome.model.entity.Palindrome;
import com.caca.palindrome.model.exception.ResourceNotFound;
import com.caca.palindrome.repository.PalindromeRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PalindromeService {

    private final PalindromeRepository palindromeRepository;

    public PalindromeService(PalindromeRepository palindromeRepository) {
        this.palindromeRepository = palindromeRepository;
    }

    public Palindrome saveResults(Set<String> palindromes) {
        return this.palindromeRepository.save(new Palindrome(palindromes));
    }

    public Palindrome findById(Long id) {
        return this.palindromeRepository.findById(id).orElseThrow(ResourceNotFound::new);
    }
}
