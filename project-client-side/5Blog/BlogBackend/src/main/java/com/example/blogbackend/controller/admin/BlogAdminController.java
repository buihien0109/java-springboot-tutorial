package com.example.blogbackend.controller.admin;

import com.example.blogbackend.model.Blog;
import com.example.blogbackend.model.request.CreateBlogRequest;
import com.example.blogbackend.model.request.UpdateBlogRequest;
import com.example.blogbackend.model.response.BlogReturn;
import com.example.blogbackend.service.admin.BlogAdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1")
@AllArgsConstructor
public class BlogAdminController {

    private final BlogAdminService blogAdminService;

    @GetMapping("/blogs")
    public ResponseEntity<?> getBlogs(@RequestParam(defaultValue = "1") int page) {
        BlogReturn blogReturn = blogAdminService.getBlogs(1);
        return ResponseEntity.ok(blogReturn);
    }

    @GetMapping("/blogs/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable int id) {
        Blog blog = blogAdminService.getBlogById(1, id);
        return ResponseEntity.ok(blog);
    }

    @PostMapping("/blogs")
    public ResponseEntity<?> createBlog(@RequestBody CreateBlogRequest request) {
        Blog blog = blogAdminService.createBlog(1, request);
        return ResponseEntity.ok(blog);
    }

    @PutMapping("/blogs/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable int id, @RequestBody UpdateBlogRequest request) {
        Blog blog = blogAdminService.updateBlog(1, id, request);
        return ResponseEntity.ok(blog);
    }

    @DeleteMapping("/blogs/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable int id) {
        blogAdminService.deleteBlog(1, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
