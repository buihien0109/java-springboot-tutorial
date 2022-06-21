package com.example.dtomapper.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AuthorDto implements Serializable {
    private Long id;
    private String name;

    public AuthorDto() {
    }

    public AuthorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
