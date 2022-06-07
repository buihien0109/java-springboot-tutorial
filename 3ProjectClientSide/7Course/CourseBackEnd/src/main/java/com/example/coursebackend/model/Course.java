package com.example.coursebackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String[] topics;
    private int supporterId;
}
