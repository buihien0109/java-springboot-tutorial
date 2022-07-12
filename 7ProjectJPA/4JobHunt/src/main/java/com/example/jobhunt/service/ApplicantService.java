package com.example.jobhunt.service;

import com.example.jobhunt.model.Applicant;
import com.example.jobhunt.repo.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepo applicantRepo;

    // Lấy danh sách tất cả ứng viên của job
    public List<Applicant> getApplicantsByJobId(int jobId) {
        return applicantRepo.getApplicantsByJobId(jobId);
    }
}
