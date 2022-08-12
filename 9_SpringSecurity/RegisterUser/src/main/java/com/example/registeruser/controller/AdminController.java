package com.example.registeruser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminController {
    // Chỉ có ROLE ADMIN mới được truy cập
    @GetMapping("/blogs")
    public String getBlogPage() {
        return "Blog Page";
    }

    // Chỉ có ROLE ADMIN mới được truy cập
    @GetMapping("/users")
    public String getUserPage() {
        return "User Page";
    }
}
