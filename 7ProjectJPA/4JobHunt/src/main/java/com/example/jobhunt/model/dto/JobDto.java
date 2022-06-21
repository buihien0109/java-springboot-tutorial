package com.example.jobhunt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private int id; // id của công việc
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private String image; // Ảnh đại diện của công việc
    private ArrayList<String> skills; // Các kỹ năng cần có
    private int salary; // Mức lương
    private int companyId; // thông tin công ty đang tuyển dụng
    private int numberOfApplicant; // Số lượng ứng viên
}
