package com.example.coursebackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;
    private String title;
    private String description;
    private String type;
    private String image;
    private double rating;
    private List<String> topics;
    private int supporterId;
}
