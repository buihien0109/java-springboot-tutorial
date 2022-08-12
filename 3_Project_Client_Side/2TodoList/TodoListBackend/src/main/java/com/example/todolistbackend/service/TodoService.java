package com.example.todolistbackend.service;

import com.example.todolistbackend.model.Todo;
import com.example.todolistbackend.request.CreateTodoRequest;
import com.example.todolistbackend.request.UpdateTodoRequest;

import java.util.List;

public interface TodoService {
    // Lấy danh sách tất cả công việc
    List<Todo> getTodos();

    // Lấy danh sách công việc theo trạng thái
    List<Todo> getTodos(boolean status);

    // Tạo công việc mới
    Todo createTodo(CreateTodoRequest request);

    // Cập nhật công việc
    Todo updateTodo(int id, UpdateTodoRequest request);

    // Xóa công việc
    void deleteTodo(int id);
}
