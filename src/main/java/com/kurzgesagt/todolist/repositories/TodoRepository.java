package com.kurzgesagt.todolist.repositories;

import com.kurzgesagt.todolist.model.Todos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todos,Long> {
    Optional<Todos> findById(Long id);
    List<Todos> findAll();
    List<Todos> findByNome(String nome);
    List<Todos> findByNomeContainingIgnoreCase(String nome);

}
