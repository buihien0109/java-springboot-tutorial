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
@Table(name = "applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // id của người ứng tuyển

    private String name; // tên người ứng tuyển

    @Column(name = "cv_path")
    private String cvPath; // Link cv

    private String description; // Mô tả khi ứng tuyển

    @Column(name = "job_id")
    private int jobId; // id của job ứng tuyển
}
