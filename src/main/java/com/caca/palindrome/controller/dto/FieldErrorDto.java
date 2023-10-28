package com.caca.palindrome.controller.dto;

public class FieldErrorDto {

    private String field;
    private String messageError;

    public FieldErrorDto() {
    }

    public FieldErrorDto(String fild, String messager) {
        this.field = fild;
        this.messageError = messager;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
