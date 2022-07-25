package com.example.sessioncookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @GetMapping("/user")
    public String getUser(Model model) {
        model.addAttribute("message", "Trang User");
        return "user";
    }

    @GetMapping("/api/v1/message")
    @ResponseBody
    public String getMessage() {
        return "Xin chào đến với trang user";
    }
}

