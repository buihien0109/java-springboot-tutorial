package com.example.jobhuntbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private int id; // id của công việc
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private City city; // Địa điểm làm việc (thành phố)
    private String address; // Địa điểm làm việc cụ thể
    private List<Skill> skills; // Các kỹ năng cần có
    private LocalDate startDate; // ngày bắt đầu tuyển dụng
    private LocalDate endDate; // ngày kết thúc tuyển dụng
    private int salary; // Mức lương
    private int companyId; // id của công ty đang tuyển dụng
}
