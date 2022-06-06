package com.example.jobhuntbackend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyRequest {
    private String name; // tên công ty
    private String website; // địa chỉ website
    private String email; // email của công ty
    private String city; // Trụ sở công ty (thành phố)
    private String address; // Địa điểm công ty cụ thể
    private String description; // Mô tả ngắn về công ty
}
