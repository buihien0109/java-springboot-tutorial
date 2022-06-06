package com.example.blogbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponse {
    private int id;
    private String title;
    private String description;
    private String content;
    private String slug;
    private String image;
    private boolean status;
    private LocalDate publishedAt;
    private LocalDate createdAt;
    private int authorId;
    private String authorName;
}
