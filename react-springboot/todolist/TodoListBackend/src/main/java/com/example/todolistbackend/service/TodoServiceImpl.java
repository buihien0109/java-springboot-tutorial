package com.example.todolistbackend.service;

import com.example.todolistbackend.exception.BadRequestException;
import com.example.todolistbackend.model.Todo;
import com.example.todolistbackend.repository.TodoRepository;
import com.example.todolistbackend.request.CreateTodoRequest;
import com.example.todolistbackend.request.UpdateTodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    // Lấy danh sách tất cả công việc
    @Override
    public List<Todo> getTodos() {
        return todoRepository.getAll();
    }

    // Lấy danh sách công việc theo trạng thái
    @Override
    public List<Todo> getTodos(boolean status) {
        return todoRepository.getByStatus(status);
    }

    // Tạo công việc mới
    @Override
    public Todo createTodo(CreateTodoRequest request) {
        if (request.getTitle().trim().length() == 0) {
            throw new BadRequestException("Title cannot be empty!");
        }

        Random rd = new Random();
        Todo todo = new Todo(rd.nextInt(1000), request.getTitle(), false);

        return todoRepository.save(todo);
    }

    // Cập nhật công việc
    @Override
    public Todo updateTodo(int id, UpdateTodoRequest request) {
        if (request.getTitle().trim().length() == 0) {
            throw new BadRequestException("Title cannot be empty!");
        }

        return todoRepository.update(id, request);
    }

    // Xóa công việc
    @Override
    public void deleteTodo(int id) {
        todoRepository.delete(id);
    }
}
