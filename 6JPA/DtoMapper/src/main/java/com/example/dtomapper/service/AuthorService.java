package com.example.dtomapper.service;

import com.example.dtomapper.dto.AuthorDto;
import com.example.dtomapper.mapper.AuthorMapperImpl;
import com.example.dtomapper.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapperImpl authorMapperImpl;


    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(author -> authorMapperImpl.authorToAuthorDto(author))
                .collect(Collectors.toList());
    }

    public List<AuthorDto> searchAuthor(String name) {
        log.info("name = " + name);
        List<AuthorDto> authorDtos = authorRepository.findAuthorByName(name);
        log.info("Thông tin = ", authorDtos);
        return authorDtos;
    }
}
