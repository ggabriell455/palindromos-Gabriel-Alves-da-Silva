package com.caca.palindrome.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Palindrome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usando FetchType.EAGER aqui porque para todo o sistema
     * essa lista de resultado é o iten mais importante, por isso não
     * precisa ser FetchType.LAZY
     */
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> resultList;

    @CreatedDate
    private LocalDateTime createdAt;

    public Palindrome() {
    }

    public Palindrome(Set<String> resultList) {
        this.resultList = resultList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<String> getResultList() {
        return resultList;
    }

    public void setResultList(Set<String> resultList) {
        this.resultList = resultList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
