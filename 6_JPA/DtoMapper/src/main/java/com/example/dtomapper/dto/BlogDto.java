package com.example.dtomapper.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlogDto implements Serializable {
    private final Integer id;
    private final String title;
    private final AuthorDto author;
}
