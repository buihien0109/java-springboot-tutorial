package com.example.todolistbackend.database;

import com.example.todolistbackend.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class FakeDB {
    public static List<Todo> todos = new ArrayList<>(List.of(
            new Todo(1, "Đi đá bóng", false),
            new Todo(2, "Làm bài tập", true),
            new Todo(3, "Đi chơi với bạn bè", true)
    ));
}
