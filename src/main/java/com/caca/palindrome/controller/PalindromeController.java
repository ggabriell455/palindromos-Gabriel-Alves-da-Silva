package com.caca.palindrome.controller;

import com.caca.palindrome.controller.dto.MatrixRequestDto;
import com.caca.palindrome.model.entity.Palindrome;
import com.caca.palindrome.service.PalindromeService;
import com.caca.palindrome.utils.PalindromeUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/palindromes")
public class PalindromeController {

    Logger logger = LoggerFactory.getLogger(PalindromeController.class);

    private final PalindromeService palindromeService;

    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @PostMapping
    public ResponseEntity<Set<String>> checkPalindromes(@RequestBody @Valid MatrixRequestDto matrixRequestDto) {
        logger.info("Method: checkPalindromes");

        Set<String> palindromes = PalindromeUtils.findPalindromes(matrixRequestDto.getMatrix());

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
    public ResponseEntity<Set<String>> findResult(@PathVariable String id) {
        logger.info("Method: findResult");

        Palindrome palindrome = this.palindromeService.findById(id);
        return ResponseEntity.ok(palindrome.getResultList());
    }

}
