package com.kurzgesagt.todolist.services;

import com.kurzgesagt.todolist.exceptions.TodoCreationException;
import com.kurzgesagt.todolist.exceptions.TodoNotFoundException;
import com.kurzgesagt.todolist.model.Todos;
import com.kurzgesagt.todolist.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServices {

    @Autowired
    TodoRepository repository;

    public Todos createTodo(Todos newTdodo){
        Todos savedTodo = repository.save(newTdodo);
        if (savedTodo == null) {
            throw new TodoCreationException("Falha ao criar o Todo");
        }
        return savedTodo;
    }

    public List<Todos> getAllTodos(){
        return repository.findAll();
    }

    //altera o estado 'realizado'
    public Todos updateTodoRealizado(Todos todo){
        todo.setRealizado(!todo.isRealizado());
        return repository.save(todo);
    }

    public Todos getTodoOrThrow(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new TodoNotFoundException("Tarefa não encontrada com id: " + id));

    }
    //pesqusia por nome
    public List<Todos> pesquisa(String name){
        if (name == null || name.trim().isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findByNomeContainingIgnoreCase(name);
        }
    }

    public Todos deleteTodo(Todos todos){
        repository.delete(todos);
        return todos;
    }
}
