package com.kurzgesagt.todolist.model.mapper;

import com.kurzgesagt.todolist.model.Todos;
import com.kurzgesagt.todolist.model.dto.TodoRequestDTO;
import com.kurzgesagt.todolist.model.dto.TodoResponseDTO;

public class TodoMapper {

    public static Todos mapToEntity(TodoRequestDTO dto) {
        Todos todo = new Todos();
        todo.setNome(dto.nome());
        todo.setDescricao(dto.descricao());
        todo.setPrioridade(dto.prioridade() != null ? Integer.parseInt(dto.prioridade()) : 1);
        return todo;
    }

    public static TodoResponseDTO mapToDTO(Todos todo) {
        return new TodoResponseDTO(todo.getId(), todo.getNome(), todo.getDescricao(), todo.getPrioridade(), todo.isRealizado());
    }
}
