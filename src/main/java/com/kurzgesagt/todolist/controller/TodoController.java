package com.kurzgesagt.todolist.controller;

import brave.Response;
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
import java.util.Optional;

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
        Optional todo = services.getTodo(id);
        if(todo == null){
            return ResponseEntity.notFound().build();
        }
        Todos toUpdate = (Todos) todo.get();
        Todos todoUpdated = services.updateTodoRealizado(toUpdate);
        TodoResponseDTO responseDTO = TodoMapper.mapToDTO(todoUpdated);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<TodoResponseDTO> deleteTodo(@PathVariable Long id){
        Optional<Todos> todo = services.getTodo(id);
        if(todo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Todos todoToDelete = todo.get();
        services.deleteTodo(todoToDelete);
        return ResponseEntity.ok().build();
    }

}
