package com.caca.palindrome.controller;

import com.caca.palindrome.controller.dto.ErrorHandleDto;
import com.caca.palindrome.controller.dto.FieldErrorDto;
import com.caca.palindrome.model.exception.ResourceNotFound;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ErrorHandleDto> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {

        List<FieldError> fieldErrors = e.getFieldErrors();
        Set<FieldErrorDto> errorList = new HashSet<>();

        if (!CollectionUtils.isEmpty(fieldErrors)) {
            errorList = fieldErrors.stream()
                    .map(el -> new FieldErrorDto(el.getField(), el.getDefaultMessage()))
                    .collect(Collectors.toSet());
        }
        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                errorList, httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorHandleDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorHandleDto> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {

        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.BAD_REQUEST.value(),
                "Estrutura da matriz está invalida!",
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                errorHandleDto
        );
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorHandleDto> resourceNotFound(ResourceNotFound e, HttpServletRequest httpServletRequest) {

        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado.",
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                errorHandleDto
        );
    }

}