package com.example.basic.controller;

import com.example.basic.request.UpsertBlogRequest;
import com.example.basic.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping("")
    public ResponseEntity<?> getBlogs() {
        return ResponseEntity.ok(blogService.getBlogs());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Integer id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createBlog(@RequestBody UpsertBlogRequest request) {
        return new ResponseEntity<>(blogService.createBlog(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBlog(@PathVariable Integer id, @RequestBody UpsertBlogRequest request) {
        return ResponseEntity.ok(blogService.updateBlog(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Integer id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
