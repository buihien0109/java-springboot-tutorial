package com.example.todolistbackend.controller;

import com.example.todolistbackend.request.CreateTodoRequest;
import com.example.todolistbackend.request.UpdateTodoRequest;
import com.example.todolistbackend.service.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getTodos(@RequestParam Optional<Boolean> status) {
        if (status.isEmpty()) {
            return ResponseEntity.ok(todoService.getTodos());
        }
        return ResponseEntity.ok(todoService.getTodos(status.get()));
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody CreateTodoRequest request) {
        return ResponseEntity.ok(todoService.createTodo(request));
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable int id, @RequestBody UpdateTodoRequest request) {
        return ResponseEntity.ok(todoService.updateTodo(id, request));
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
