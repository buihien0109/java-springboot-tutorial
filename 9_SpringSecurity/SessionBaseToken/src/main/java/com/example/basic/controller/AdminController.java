package com.example.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    // Vào trang blogs -> Cần quyền EDITOR, ADMIN
    @GetMapping("/admin/blogs")
    public String getBlogPage() {
        return "blog";
    }

    // Vào trang blogs -> Cần quyền ADMIN
    @GetMapping("/admin/users")
    public String getUserPage() {
        return "user";
    }
}
