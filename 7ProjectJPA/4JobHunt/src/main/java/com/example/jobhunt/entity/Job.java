package com.example.jobhunt.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // id của công việc

    private String title; // Tiêu đề của công việc

    private String description; // Mô tả cho công việc

    private String image; // Ảnh đại diện của công việc

    @Type(type = "json")
    @Column(name = "skills", columnDefinition = "json")
    private ArrayList<String> skills; // Các kỹ năng cần có

    private int salary; // Mức lương

    @Column(name = "company_id")
    private int companyId; // id của công ty đang tuyển dụng
}
