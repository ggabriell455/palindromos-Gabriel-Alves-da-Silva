package com.caca.palindrome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CacaPalindromeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacaPalindromeApplication.class, args);
    }

}
