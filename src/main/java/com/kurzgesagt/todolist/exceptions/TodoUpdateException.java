package com.kurzgesagt.todolist.exceptions;

public class TodoUpdateException extends RuntimeException{
    public TodoUpdateException(String message) {
        super(message);
    }
}
