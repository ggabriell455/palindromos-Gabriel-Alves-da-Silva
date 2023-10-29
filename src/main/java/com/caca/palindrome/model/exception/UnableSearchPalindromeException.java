package com.caca.palindrome.model.exception;


public class UnableSearchPalindromeException extends RuntimeException {

    public UnableSearchPalindromeException() {
    }

    public UnableSearchPalindromeException(String message) {
        super(message);
    }
}
