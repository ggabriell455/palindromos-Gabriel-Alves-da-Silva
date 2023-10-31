package com.caca.palindrome.controller;

import com.caca.palindrome.controller.dto.ErrorHandleDto;
import com.caca.palindrome.controller.dto.FieldErrorDto;
import com.caca.palindrome.model.exception.ResourceNotFoundException;
import com.caca.palindrome.model.exception.UnableSearchPalindromeException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorHandleDto.class))}),
    })
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorHandleDto.class))}),
    })
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorHandleDto> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {

        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.BAD_REQUEST.value(),
                "Estrutura da matriz está inválida",
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                errorHandleDto
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorHandleDto.class))}),
    })
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorHandleDto> illegalArgumentException(IllegalArgumentException e, HttpServletRequest httpServletRequest) {

        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.BAD_REQUEST.value(),
                "Verifique os dados da requisição realizada",
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                errorHandleDto
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorHandleDto.class))}),
    })
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorHandleDto> resourceNotFound(ResourceNotFoundException e, HttpServletRequest httpServletRequest) {

        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                errorHandleDto
        );
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "500",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorHandleDto.class))}),
    })
    @ExceptionHandler(UnableSearchPalindromeException.class)
    public ResponseEntity<ErrorHandleDto> unableSearchPalindromeException(UnableSearchPalindromeException e, HttpServletRequest httpServletRequest) {

        ErrorHandleDto errorHandleDto = new ErrorHandleDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage(),
                httpServletRequest.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                errorHandleDto
        );
    }

}
