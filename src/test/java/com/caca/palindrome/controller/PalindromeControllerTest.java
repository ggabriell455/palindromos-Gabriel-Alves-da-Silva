package com.caca.palindrome.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PalindromeControllerTest {

    private final String BASE_URL = "/palindromes";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should return a value, the value must be a list and have 4 elements and the header must have Location")
    void checkPalindromesSuccess() throws Exception {

        String body = """
                {
                  "matrix": [
                    ["A", "O", "S", "S", "O"],
                    ["Y", "R", "Z", "X", "L"],
                    ["J", "S", "A", "P", "M"],
                    ["J", "K", "P", "R", "Z"],
                    ["Y", "L", "E", "R", "A"]
                  ]
                }""";

        this.mockMvc.perform(post(BASE_URL)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    @DisplayName("Should return erro 400 beacuse structure is invalid")
    void checkPalindromesStructuralError() throws Exception {

        String body = """
                {
                  "matrix": [
                    ["A", "O", "S", "S", 1],
                    ["Y", "R", "Z", "X", "L"],
                    ["J", "S", "A", "P", "M"],
                    ["J", "K", "P", "R", "Z"],
                    ["Y", "L", "E", "R", "A"]
                  ]
                }""";

        this.mockMvc.perform(post(BASE_URL)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.header().doesNotExist(HttpHeaders.LOCATION))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error").value("Estrutura da matriz está inválida"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.path").value(BASE_URL));
    }

    @Test
    @DisplayName("Should return erro 400 beacuse matrix don't is square")
    void checkPalindromesMatrixNotSquareError() throws Exception {

        String body = """
                {
                  "matrix": [
                    ["A", "O", "S", "S", "0"],
                    ["Y", "R", "Z", "X", "L"],
                    ["J", "S", "A", "P", "M"],
                    ["J", "K", "P", "R", "Z"]
                  ]
                }""";

        this.mockMvc.perform(post(BASE_URL)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.header().doesNotExist(HttpHeaders.LOCATION))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0].field").value("matrix"))
                .andExpect(jsonPath("$.errors[0].messageError").value("A matriz não é quadrada"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.path").value(BASE_URL));
    }

    @Test
    @DisplayName("Should return erro 400 beacuse body is empty")
    void checkPalindromesMatrixNullError() throws Exception {

        String body = "";

        this.mockMvc.perform(post(BASE_URL)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.header().doesNotExist(HttpHeaders.LOCATION))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.error").value("Estrutura da matriz está inválida"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.path").value(BASE_URL));
    }

    @Test
    @Sql({"/data/palindrome-controller-test.sql"})
    void findResultSuccess() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/b29ed83b-ba75-468f-b34d-c49ba525f463"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void findResultNotFound() throws Exception {

        this.mockMvc.perform(get(BASE_URL + "/9147b7df-dfd7-4c38-83c5-514324db74b6"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.error").value("Recurso não encontrado"))
                .andExpect(jsonPath("$.timestamp").isNotEmpty())
                .andExpect(jsonPath("$.path").isNotEmpty());
    }
}