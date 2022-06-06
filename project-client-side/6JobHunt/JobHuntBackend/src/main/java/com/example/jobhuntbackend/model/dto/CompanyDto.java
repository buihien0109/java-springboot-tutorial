package com.example.jobhuntbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private int id; // Id của công ty
    private String name; // Tên công ty
    private String logoPath; // Logo đại diện
    private String city; // Trụ sở công ty (thành phố)
    private int numberOfJobs; // Số lượng công việc đang ứng tuyển
}
