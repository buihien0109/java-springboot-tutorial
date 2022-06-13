package com.example.coursebackend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseRequest {
    private String title;
    private String description;
    private String type;
    private String[] topics;
    private int price;
    private int supporterId;
}
