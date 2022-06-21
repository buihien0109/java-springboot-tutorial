package com.example.jobhunt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private ArrayList<String> skills; // Các kỹ năng cần có
    private int salary; // Mức lương
}
