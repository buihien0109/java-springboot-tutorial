package com.example.userthymeleaf.controller;

import com.example.userthymeleaf.serivice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class UserViewController {

    private final UserService userService;

    @GetMapping(value = {"/", "/users"})
    public String getIndexPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("users/create")
    public String getCreatePage() {
        return "create";
    }

    @GetMapping("/users/{id}")
    public String getDetailPage(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "detail";
    }
}
