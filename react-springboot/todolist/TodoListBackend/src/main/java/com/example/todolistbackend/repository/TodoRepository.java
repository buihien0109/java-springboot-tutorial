package com.example.todolistbackend.repository;

import com.example.todolistbackend.model.Todo;
import com.example.todolistbackend.request.UpdateTodoRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    List<Todo> getAll();

    List<Todo> getByStatus(boolean status);

    Optional<Todo> findById(int id);

    Todo save(Todo todo);

    Todo update(int id, UpdateTodoRequest request);

    void delete(int id);
}
