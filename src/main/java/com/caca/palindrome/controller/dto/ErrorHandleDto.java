package com.caca.palindrome.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorHandleDto {

    private int code;
    private String messageCode;
    private String error;
    private Set<FieldErrorDto> errors;

    public ErrorHandleDto() {
    }

    public ErrorHandleDto(HttpStatus httpStatus, Set<FieldErrorDto> errors) {
        this.code = httpStatus.value();
        this.messageCode = httpStatus.getReasonPhrase();
        this.errors = errors;
    }

    public ErrorHandleDto(HttpStatus httpStatus, String error) {
        this.code = httpStatus.value();
        this.messageCode = httpStatus.getReasonPhrase();
        this.error = error;
    }

    public ErrorHandleDto(int code, String messagerCode, String error) {
        this.code = code;
        this.messageCode = messagerCode;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
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
}
