package com.example.todobackend.service;

import com.example.todobackend.exception.BadRequestException;
import com.example.todobackend.exception.NotFoundException;
import com.example.todobackend.entity.Todo;
import com.example.todobackend.repository.TodoRepository;
import com.example.todobackend.request.CreateTodoRequest;
import com.example.todobackend.request.UpdateTodoRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // Lấy danh sách công việc theo trạng thái
    public List<Todo> getTodos(String status) {
        return switch (status) {
            case "true" -> todoRepository.getTodosByStatus(true);
            case "false" -> todoRepository.getTodosByStatus(false);
            default -> todoRepository.findAll();
        };
    }

    // Tìm kiếm công việc theo id
    public Todo getTodoById(int id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isPresent()) {
            return todoOptional.get();
        }

        throw new NotFoundException("Not found with id = " + id);
    }

    // Tạo công việc mới
    public Todo createTodo(CreateTodoRequest request) {
        if(request.getTitle() == null) {
            throw new BadRequestException("Todo title cannot be empty!");
        }

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setStatus(false);

        todoRepository.save(todo);
        return todo;
    }

    // Cập nhật công việc
    public Todo updateTodo(int id, UpdateTodoRequest request) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isEmpty()) {
            throw new NotFoundException("Not found with id = " + id);
        }

        Todo todo = todoOptional.get();

        todo.setTitle(request.getTitle());
        todo.setStatus(request.isStatus());

        todoRepository.save(todo);
        return todo;
    }

    // Xóa công việc
    public void deleteTodo(int id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isEmpty()) {
            throw new NotFoundException("Not found with id = " + id);
        }
        todoRepository.delete(todoOptional.get());
    }
}
