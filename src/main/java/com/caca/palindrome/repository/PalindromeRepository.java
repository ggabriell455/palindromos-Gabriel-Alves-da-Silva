package com.caca.palindrome.repository;

import com.caca.palindrome.model.entity.Palindrome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PalindromeRepository extends JpaRepository<Palindrome, UUID> {
}
