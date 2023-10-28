package com.caca.palindrome.controller;

import com.caca.palindrome.controller.dto.ErrorHandleDto;
import com.caca.palindrome.controller.dto.FieldErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorHandleDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getFieldErrors();
        Set<FieldErrorDto> errorList = new HashSet<>();

        if (!CollectionUtils.isEmpty(fieldErrors)) {
            errorList = fieldErrors.stream()
                    .map(el -> new FieldErrorDto(el.getField(), el.getDefaultMessage()))
                    .collect(Collectors.toSet());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorHandleDto(HttpStatus.BAD_REQUEST, errorList)
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorHandleDto> methodArgumentNotValidException(HttpMessageNotReadableException e) {

        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.BAD_REQUEST, "Estrutura da matriz está invalida!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                errorHandleDto
        );
    }

}
