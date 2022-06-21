package com.example.jobhunt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateApplicantRequest {
    private String name; // tên người ứng tuyển
    private MultipartFile file; // Link cv
    private String description; // Mô tả khi ứng tuyển
}
