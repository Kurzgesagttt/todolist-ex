package com.kurzgesagt.todolist.model.mapper;

import com.kurzgesagt.todolist.model.Todos;
import com.kurzgesagt.todolist.model.dto.TodoRequestDTO;

public class TodoMapper {

    public static Todos mapToEntity(TodoRequestDTO dto) {
        Todos todo = new Todos();
        todo.setNome(dto.nome());
        todo.setDescricao(dto.descricao());
        todo.setPrioridade(dto.prioridade() != null ? Integer.parseInt(dto.prioridade()) : 1);
        return todo;
    }
}
