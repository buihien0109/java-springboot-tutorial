package com.example.blogbackend.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1")
public class BlogAdminController {
    @GetMapping("/blogs")
    public ResponseEntity<?> getBlogs() {

        return null;
    }

    @PostMapping("/blogs")
    public ResponseEntity<?> createBlog() {

        return null;
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable String id) {

        return null;
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable String id) {

        return null;
    }
}
