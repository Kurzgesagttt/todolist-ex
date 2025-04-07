package com.kurzgesagt.todolist.exceptions;

import brave.Response;
import com.kurzgesagt.todolist.model.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(TodoCreationException.class)
    public ResponseEntity<ErrorResponseDTO> handleTodoNotFoundException(TodoNotFoundException ex){
        ErrorResponseDTO erroResponse = new ErrorResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    @ExceptionHandler(TodoCreationException.class)
    public ResponseEntity<ErrorResponseDTO> handleTodoCreationException(TodoCreationException ex){
        ErrorResponseDTO erroResponse = new ErrorResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(Exception.class)



}
