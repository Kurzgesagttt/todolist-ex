package com.kurzgesagt.todolist.controller;

import com.kurzgesagt.todolist.model.Todos;
import com.kurzgesagt.todolist.model.dto.TodoRequestDTO;
import com.kurzgesagt.todolist.model.mapper.TodoMapper;
import com.kurzgesagt.todolist.services.TodoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoServices services;

    @PostMapping
    public ResponseEntity<Void> createTodo(@Valid @RequestBody TodoRequestDTO dto){
        Todos todo = TodoMapper.mapToEntity(dto);


        return ResponseEntity.created().body(services.createTodo());
    }
}
