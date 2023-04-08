package com.example.customprovider.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
        return ResponseEntity.ok("admin");
    }

    @PreAuthorize("hasRole('AUTHOR')")
    @GetMapping("author")
    public ResponseEntity<?> getAuthor() {
        return ResponseEntity.ok("admin");
    }

    // Lấy thông tin người dùng sử dụng SecurityContextHolder
    @PreAuthorize("hasRole('USER')")
    @GetMapping("user-info")
    public ResponseEntity<?> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication);
    }
}
