package com.kurzgesagt.todolist.model.dto;

public record TodoResponseDTO(Long id, String nome, String descricao, int prioridade, boolean realizado) {
}
