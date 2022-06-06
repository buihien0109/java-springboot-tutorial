package com.example.jobhuntbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private int id; // id của công việc
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private String city; // Địa điểm làm việc (thành phố)
    private String address; // Địa điểm làm việc cụ thể
    private String image; // Ảnh đại diện của công việc
    private String[] skills; // Các kỹ năng cần có
    private String startDate; // ngày bắt đầu tuyển dụng
    private String endDate; // ngày kết thúc tuyển dụng
    private int salary; // Mức lương
    private int companyId; // id của công ty đang tuyển dụng
}
