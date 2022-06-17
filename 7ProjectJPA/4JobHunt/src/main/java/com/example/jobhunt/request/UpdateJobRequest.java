package com.example.jobhunt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobRequest {
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private ArrayList<String> skills; // Các kỹ năng cần có
    private int salary; // Mức lương
    private int companyId; // id của công ty đang tuyển dụng
}
