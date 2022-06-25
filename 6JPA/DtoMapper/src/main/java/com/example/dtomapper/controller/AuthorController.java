package com.example.dtomapper.controller;

import com.example.dtomapper.dto.AuthorDto;
import com.example.dtomapper.dto.BlogDto;
import com.example.dtomapper.entity.Author;
import com.example.dtomapper.repository.AuthorInfo;
import com.example.dtomapper.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @GetMapping("/authors")
    public List<AuthorDto> getBlogs() {
        return  authorService.getAuthors();
    }

    @GetMapping("/authors/search")
    public List<AuthorDto> searchAuthor(@RequestParam String name) {
        return authorService.searchAuthor(name);
    }
}
