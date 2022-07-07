package com.example.jobhunt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    private int id; // id của người ứng tuyển

    private String name; // tên người ứng tuyển

    private String cvPath; // Link cv

    private String description; // Mô tả khi ứng tuyển

    private int jobId; // id của job ứng tuyển
}
