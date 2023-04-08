package com.example.methodsecurity.controller;

import com.example.methodsecurity.security.anotation.IsAuthor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WebController {
    @GetMapping("hello")
    public ResponseEntity<?> getHello() {
        return ResponseEntity.ok("Hello");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok("Profile");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin")
    public ResponseEntity<?> getAdmin() {
        return ResponseEntity.ok("Admin");
    }

    @Secured("ROLE_USER")
    @GetMapping("user")
    public ResponseEntity<?> getUser() {
        return ResponseEntity.ok("User");
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("admin/blog")
    public ResponseEntity<?> getBlogAdmin() {
        return ResponseEntity.ok("Blog Admin");
    }

    @IsAuthor
    @GetMapping("author")
    public ResponseEntity<?> getAuthor() {
        return ResponseEntity.ok("Author");
    }
}

