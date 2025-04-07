package com.kurzgesagt.todolist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao", nullable = false)
    private String descricao;
    @Column(name = "prioridade")
    private int prioridade = 1;
    @Column(name = "realizado")
    private boolean realizado = false;

    public Todos(String nome, int prioridade, String descricao, boolean realizado) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.descricao = descricao;
        this.realizado = realizado;
    }

    public Todos() {
    }

    public Long getId() {
        return id;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
