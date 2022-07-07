package com.example.jobhunt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private int id; // id của công việc
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private List<String> skills; // Các kỹ năng cần có
    private int salary; // Mức lương
    private int companyId; // id của công ty đang tuyển dụng
}
