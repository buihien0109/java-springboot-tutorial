package com.example.jobhunt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {
    private String title; // Tiêu đề của công việc
    private String description; // Mô tả cho công việc
    private String city; // Địa điểm làm việc (thành phố)
    private String address; // Địa điểm làm việc cụ thể
    private String[] skills; // Các kỹ năng cần có
    private LocalDate startDate; // ngày bắt đầu tuyển dụng
    private LocalDate endDate; // ngày kết thúc tuyển dụng
    private int salary; // Mức lương
}
