package com.example.coursebackend.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private int id;
    private String title;
    private String description;
    private String type;
    private String image;
    private double rating;
    private String[] topics;
    private int price;
    private int supporterId;
}
