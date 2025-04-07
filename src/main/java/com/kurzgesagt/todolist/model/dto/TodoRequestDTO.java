package com.kurzgesagt.todolist.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoRequestDTO(@NotBlank(message = "Campo obrigatorio")
                            @Size(min = 3, max = 100, message = "Campo fora do tamanho padrao")
                             String nome,
                             @NotBlank(message = "Campo obrigatorio")
                             @Size(min = 3, max = 100, message = "Campo fora do tamanho padrao")
                             String descricao,
                             @Size(max = 5, message = "Campo com tamanho exagerado")
                             String prioridade) {
}