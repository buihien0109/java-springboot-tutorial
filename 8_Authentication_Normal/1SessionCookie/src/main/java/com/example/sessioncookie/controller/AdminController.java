package com.example.sessioncookie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String getUser(Model model) {
        model.addAttribute("message", "Trang Admin");
        return "admin";
    }

    @GetMapping("/api/v1/admin/message")
    @ResponseBody
    public String getMessage() {
        return "Xin chào đến với trang admin";
    }
}
