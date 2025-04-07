package com.kurzgesagt.todolist.model.dto;

public record ErrorResponseDTO(String message, int statusCode) {

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
