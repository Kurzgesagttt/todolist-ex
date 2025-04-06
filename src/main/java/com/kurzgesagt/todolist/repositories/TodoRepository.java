package com.kurzgesagt.todolist.repositories;

import com.kurzgesagt.todolist.model.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todos,Long> {
}
