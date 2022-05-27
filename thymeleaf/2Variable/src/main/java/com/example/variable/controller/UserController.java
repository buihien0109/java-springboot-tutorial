package com.example.variable.controller;

import com.example.variable.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("user", userService.getUserById(1));
        model.addAttribute("otherUser", userService.getUserById(2));
        model.addAttribute("users", userService.getUsers());

        model.addAttribute("id", 3);
        model.addAttribute("page", 3);
        model.addAttribute("limit", 10);
        model.addAttribute("lang", "en");
        return "index";
    }
}
