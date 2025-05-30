package com.kurzgesagt.todolist.controller;

import com.kurzgesagt.todolist.model.Todos;
import com.kurzgesagt.todolist.model.dto.TodoRequestDTO;
import com.kurzgesagt.todolist.model.dto.TodoResponseDTO;
import com.kurzgesagt.todolist.model.mapper.TodoMapper;
import com.kurzgesagt.todolist.services.TodoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        services.createTodo(todo);
        URI location = URI.create(String.format("/todos/%s", todo.getId()));
        return ResponseEntity.created(location).build();

    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos(){
        List<Todos> list = services.getAllByPrioridade();
        List<TodoResponseDTO> responseDTO = list.stream()
                .map(TodoMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchTodoByName(@RequestParam(required = false) String nome){
        if (nome == null || nome.trim().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Parâmetro 'nome' não informado.");
        }

        List<Todos> list = services.pesquisa(nome);

        if (list.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Nenhuma tarefa encontrada com esse nome.");
        }

        List<TodoResponseDTO> listDto = list.stream()
                .map(TodoMapper::mapToDTO)
                .toList();

        return ResponseEntity.ok(listDto);
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<TodoResponseDTO> updateTodoStatus(@PathVariable Long id){
        Todos todo = services.getTodoOrThrow(id);
        if(todo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Todos todoUpdated = services.updateTodoRealizado(todo);
        TodoResponseDTO responseDTO = TodoMapper.mapToDTO(todoUpdated);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<TodoResponseDTO> deleteTodo(@PathVariable Long id){
        Todos todo = services.getTodoOrThrow(id);

        services.deleteTodo(todo);
        return ResponseEntity.ok().build();
    }

}
