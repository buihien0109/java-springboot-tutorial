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
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // id của công ty

    private String name; // tên công ty

    @Column(name = "logo_path")
    private String logoPath; // logo đại diện

    private String website; // địa chỉ website

    private String email; // email của công ty

    private String city; // Trụ sở công ty (thành phố)

    private String address; // Địa điểm công ty cụ thể

    private String description; // Mô tả ngắn về công ty
}
