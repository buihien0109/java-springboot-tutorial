package com.example.blogbackend.controller;

import com.example.blogbackend.model.Blog;
import com.example.blogbackend.model.response.BlogReturn;
import com.example.blogbackend.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping(value = {"/blogs"})
    public ResponseEntity<?> getBlogs(@RequestParam(defaultValue = "1") int page) {
        BlogReturn blogReturn = blogService.getBlogs(page);
        return ResponseEntity.ok(blogReturn);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable int id) {
        Blog blog = blogService.getBlogById(id);
        return ResponseEntity.ok(blog);
    }
}
