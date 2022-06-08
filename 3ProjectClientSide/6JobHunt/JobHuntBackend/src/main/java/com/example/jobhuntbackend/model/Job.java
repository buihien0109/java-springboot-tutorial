package com.example.jobhuntbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private int id; // id của công việc
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private String image; // Ảnh đại diện của công việc
    private String[] skills; // Các kỹ năng cần có
    private int salary; // Mức lương
    private int companyId; // id của công ty đang tuyển dụng
}
