package com.caca.palindrome.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorHandleDto {

    private int status;
    private String error;
    private Set<FieldErrorDto> errors;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:sszz", locale = "pt-BR", timezone = "GTM-3")
    private LocalDateTime timestamp;
    private String path;

    public ErrorHandleDto() {
    }

    public ErrorHandleDto(int status, String error, Set<FieldErrorDto> errors, String path) {
        this.status = status;
        this.error = error;
        this.errors = errors;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorHandleDto(int status, String error, String path) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorHandleDto(int status, String error, Set<FieldErrorDto> errors, LocalDateTime timestamp, String path) {
        this.status = status;
        this.error = error;
        this.errors = errors;
        this.timestamp = timestamp;
        this.path = path;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Set<FieldErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(Set<FieldErrorDto> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
