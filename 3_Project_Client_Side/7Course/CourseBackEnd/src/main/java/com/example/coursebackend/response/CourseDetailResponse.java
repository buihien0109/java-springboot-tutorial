package com.example.coursebackend.response;

import com.example.coursebackend.model.Supporter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailResponse {
    private int id;
    private String title;
    private String description;
    private String type;
    private String image;
    private double rating;
    private String[] topics;
    private Supporter supporter;
}
