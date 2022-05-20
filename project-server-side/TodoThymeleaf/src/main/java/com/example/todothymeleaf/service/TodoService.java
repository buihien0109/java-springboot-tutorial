package com.example.todothymeleaf.service;


import com.example.todothymeleaf.exception.BadRequestException;
import com.example.todothymeleaf.exception.NotFoundException;
import com.example.todothymeleaf.model.Todo;
import com.example.todothymeleaf.request.CreateTodoRequest;
import com.example.todothymeleaf.request.UpdateTodoRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TodoService {
    // Danh sách công việc
    private List<Todo> todos = new ArrayList<>();

    // Trong constructor tạo 1 số công việc vào danh sách công việc
    public TodoService() {
        Random rd = new Random();
        todos.add(new Todo(rd.nextInt(1000), "Đi đá bóng", false));
        todos.add(new Todo(rd.nextInt(1000), "Làm bài tập", true));
        todos.add(new Todo(rd.nextInt(1000), "Đi chơi với bạn bè", true));
    }

    // Lấy danh sách công việc theo trạng thái
    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "true" -> todos.stream().filter(Todo::isStatus).collect(Collectors.toList());
            case "false" -> todos.stream().filter(p -> !p.isStatus()).collect(Collectors.toList());
            default -> todos;
        };
    }

    // Tìm kiếm công việc theo id
    public Todo getTodoById(int id) {
        Optional<Todo> todoOptional = findById(id);
        if (todoOptional.isPresent()) {
            return todoOptional.get();
        }

        throw new NotFoundException("Not found with id = " + id);
    }

    // Tạo công việc mới
    public Todo createTodo(CreateTodoRequest request) {
        if (request.getTitle() == null) {
            throw new BadRequestException("Todo title cannot be empty!");
        }

        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(1000), request.getTitle(), false);

        todos.add(todo);
        return todo;
    }

    // Cập nhật công việc
    public Todo updateTodo(int id, UpdateTodoRequest request) {
        Optional<Todo> todoOptional = findById(id);
        if (todoOptional.isEmpty()) {
            throw new NotFoundException("Not found with id = " + id);
        }

        Todo todo = todoOptional.get();

        todo.setTitle(request.getTitle());
        todo.setStatus(request.isStatus());

        return todo;
    }

    // Xóa công việc
    public void deleteTodo(int id) {
        Optional<Todo> todoOptional = findById(id);
        if (todoOptional.isEmpty()) {
            throw new NotFoundException("Not found with id = " + id);
        }
        todos.removeIf(todo -> todo.getId() == id);
    }

    // Helper method : Tìm kiếm công việc theo id
    public Optional<Todo> findById(int id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst();
    }
}
