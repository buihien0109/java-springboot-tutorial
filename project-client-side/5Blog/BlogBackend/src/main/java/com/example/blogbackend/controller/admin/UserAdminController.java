package com.example.blogbackend.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1")
public class UserAdminController {
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {

        return null;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser() {

        return null;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {

        return null;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id) {

        return null;
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {

        return null;
    }
}
