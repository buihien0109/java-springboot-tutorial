package com.example.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    // Cho phép ai cũng có thể truy cập
    @GetMapping("/")
    public String getHome() {
        return "Home page";
    }

    // Chỉ authenticate mới có thể truy cập
    @GetMapping("/profile")
    public String profilePage() {
        return "Profile page";
    }
}
