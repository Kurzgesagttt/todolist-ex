package com.kurzgesagt.todolist.repositories;

import com.kurzgesagt.todolist.model.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todos,Long> {
    List<Todos> findByName(String name);
}
