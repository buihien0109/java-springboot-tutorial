package com.example.dtomapper.controller;

import com.example.dtomapper.dto.BlogDto;
import com.example.dtomapper.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public List<BlogDto> getBlogs() {
        return blogService.getBlogs();
    }
}
