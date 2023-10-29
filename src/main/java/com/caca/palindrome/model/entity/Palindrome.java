package com.caca.palindrome.model.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "palindrome")
@EntityListeners(AuditingEntityListener.class)
public class Palindrome {

    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Usando FetchType.EAGER aqui porque para todo o sistema
     * essa lista de resultado é o iten mais importante, por isso não
     * precisa ser FetchType.LAZY
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "palindrome_result_list", joinColumns = @JoinColumn(name = "palindrome_id "))
    private Set<String> resultList;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Palindrome() {
    }

    public Palindrome(Set<String> resultList) {
        this.resultList = resultList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
