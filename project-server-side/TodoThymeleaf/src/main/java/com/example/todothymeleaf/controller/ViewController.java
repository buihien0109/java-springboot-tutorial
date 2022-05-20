package com.example.todothymeleaf.controller;

import com.example.todothymeleaf.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ViewController {

    private final TodoService todoService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("todos", todoService.getTodos(""));
        return "index";
    }
}
