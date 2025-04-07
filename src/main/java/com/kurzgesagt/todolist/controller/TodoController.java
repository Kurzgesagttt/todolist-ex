package com.kurzgesagt.todolist.controller;

import com.kurzgesagt.todolist.model.Todos;
import com.kurzgesagt.todolist.model.dto.TodoRequestDTO;
import com.kurzgesagt.todolist.model.dto.TodoResponseDTO;
import com.kurzgesagt.todolist.model.mapper.TodoMapper;
import com.kurzgesagt.todolist.services.TodoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoServices services;

    @PostMapping
    public ResponseEntity<TodoResponseDTO> createTodo(@Valid @RequestBody TodoRequestDTO dto){
        Todos todo = TodoMapper.mapToEntity(dto);
        URI location = URI.create(String.format("/todos/%s", todo.getId()));
        services.createTodo(todo);
        return ResponseEntity.created(location).build();

    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos(){
        List<Todos> list = services.getAllTodos();
        List<TodoResponseDTO> responseDTO = list.stream()
                .map(TodoMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TodoResponseDTO> updateTodoStatus(@PathVariable Long id){
        Todos todo = services.getTodo(id);
        if(todo == null){
            return ResponseEntity.notFound().build();
        }
        
    }



}
