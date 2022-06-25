package com.example.dtomapper.service;

import com.example.dtomapper.dto.BlogDto;
import com.example.dtomapper.mapper.AuthorMapper;
import com.example.dtomapper.mapper.AuthorMapperImpl;
import com.example.dtomapper.mapper.BlogMapperImpl;
import com.example.dtomapper.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogMapperImpl blogMapperImpl;

    public List<BlogDto> getBlogs() {
        return blogRepository.findAll().stream()
                .map(blog -> blogMapperImpl.blogToBlogDto(blog))
                .collect(Collectors.toList());
    }
}
