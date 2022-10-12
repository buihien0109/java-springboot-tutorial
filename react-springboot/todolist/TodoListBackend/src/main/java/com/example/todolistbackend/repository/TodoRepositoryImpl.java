package com.example.todolistbackend.repository;

import com.example.todolistbackend.database.FakeDB;
import com.example.todolistbackend.exception.NotFoundException;
import com.example.todolistbackend.model.Todo;
import com.example.todolistbackend.request.UpdateTodoRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TodoRepositoryImpl implements TodoRepository{
    @Override
    public List<Todo> getAll() {
        return FakeDB.todos;
    }

    @Override
    public List<Todo> getByStatus(boolean status) {
        return FakeDB.todos
                .stream()
                .filter(todo -> todo.isStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Todo> findById(int id) {
        return FakeDB.todos
                .stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();
    }

    @Override
    public Todo save(Todo todo) {
        FakeDB.todos.add(todo);
        return todo;
    }

    @Override
    public Todo update(int id, UpdateTodoRequest request) {
        Todo todoUpdate = findById(id).orElseThrow(() -> {
           throw new NotFoundException("Not found todo with id = " + id);
        });

        todoUpdate.setTitle(request.getTitle());
        todoUpdate.setStatus(request.isStatus());

        return todoUpdate;
    }

    @Override
    public void delete(int id) {
        Todo todo = findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found todo with id = " + id);
        });

        FakeDB.todos.removeIf(t -> t.getId() == id);
    }
}
