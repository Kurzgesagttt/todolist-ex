package com.kurzgesagt.todolist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "todos")
public class Todos {

    @Column(name = "id")
    private Long id;




}
