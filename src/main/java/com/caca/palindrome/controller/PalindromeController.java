package com.caca.palindrome.controller;

import com.caca.palindrome.controller.dto.MatrixRequestDto;
import com.caca.palindrome.model.entity.Palindrome;
import com.caca.palindrome.service.PalindromeService;
import com.caca.palindrome.utils.PalindromeUtis;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/palindromes")
public class PalindromeController {

    private final PalindromeService palindromeService;

    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @PostMapping
    public ResponseEntity<Set<String>> checkPalindromes(@RequestBody @Valid MatrixRequestDto matrixRequestDto) {
        char[][] matrix = matrixRequestDto.getMatrix();

        Set<String> palindromes = PalindromeUtis.findPalindromes(matrix);

        Palindrome palindrome = this.palindromeService.saveResults(palindromes);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(palindrome.getId())
                .toUri();

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, location.toString())
                .body(palindrome.getResultList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Set<String>> checkPalindromes(@PathVariable Long id) {
        Palindrome palindrome = this.palindromeService.findById(id);
        return ResponseEntity.ok(palindrome.getResultList());
    }

}
