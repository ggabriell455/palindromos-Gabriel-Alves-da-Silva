package com.caca.palindrome.controller;

import com.caca.palindrome.controller.dto.MatrixRequestDto;
import com.caca.palindrome.service.PalindromeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/matrix")
public class PalindromeController {

    private final PalindromeService palindromeService;

    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @PostMapping("/check-palindromes")
    public ResponseEntity<Set<String>> checkPalindromes(@RequestBody @Valid MatrixRequestDto matrixRequestDto) {
        Set<String> palindromes = this.palindromeService.findPalindromes(matrixRequestDto.getMatrix());
        return ResponseEntity.ok(palindromes);
    }

}
