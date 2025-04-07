package com.kurzgesagt.todolist.exceptions;

public class TodoCreationException extends RuntimeException {
    public TodoCreationException(String message) {
        super(message);
    }
}
