package com.example.jobhunt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private int id; // id của công ty
    private String name; // tên công ty
    private String logoPath; // logo đại diện
    private String website; // địa chỉ website
    private String email; // email của công ty
    private String city; // Trụ sở công ty (thành phố)
    private String description; // Mô tả ngắn về công ty
}
