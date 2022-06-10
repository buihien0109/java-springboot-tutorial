package com.example.jobhunt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // id của công việc

    private String title; // Tiêu đề của công việc

    private String description; // Mô tả cho công việc

    private String image; // Ảnh đại diện của công việc

    private List[] skills; // Các kỹ năng cần có

    private int salary; // Mức lương

    @Column(name = "company_id")
    private int companyId; // id của công ty đang tuyển dụng
}
