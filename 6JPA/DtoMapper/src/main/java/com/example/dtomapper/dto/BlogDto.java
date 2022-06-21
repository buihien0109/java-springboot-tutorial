package com.example.dtomapper.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class BlogDto implements Serializable {
    private Integer id;
    private String name;
    private AuthorDto author;

    public BlogDto() {
    }

    public BlogDto(Integer id, String name, AuthorDto author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
