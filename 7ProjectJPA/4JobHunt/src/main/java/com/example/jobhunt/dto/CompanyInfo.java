package com.example.jobhunt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfo {
    private int id; // id của công ty
    private String name; // tên công ty
    private String logoPath; // logo đại diện
    private String city; // Trụ sở công ty (thành phố)
    private int numberJob; // Sô lượng công việc
}
