package com.example.jobhunt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private int id; // id của công ty
    private String name; // tên công ty
    private String logoPath; // logo đại diện
    private String city; // Trụ sở công ty (thành phố)
    private String description; // Mô tả ngắn về công ty
}
