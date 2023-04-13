package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/")
    public ResponseEntity<?> getHome() {
        return ResponseEntity.ok("Home");
    }

    @GetMapping("user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok("User");
    }

    @GetMapping("admin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("Admin");
    }

    @GetMapping("author")
    public ResponseEntity<?> getAuthor() {
        return ResponseEntity.ok("Author");
    }
}
