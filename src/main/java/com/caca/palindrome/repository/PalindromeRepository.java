package com.caca.palindrome.repository;

import com.caca.palindrome.model.entity.Palindrome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalindromeRepository extends JpaRepository<Palindrome, Long> {
}
