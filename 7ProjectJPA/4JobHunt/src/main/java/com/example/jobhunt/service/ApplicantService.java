package com.example.jobhunt.service;

import com.example.jobhunt.model.Applicant;
import com.example.jobhunt.repo.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantService {
    @Autowired
    private ApplicantRepo applicantRepo;

    public List<Applicant> getApplicantsByJobId(int jobId) {
        return applicantRepo.getApplicantsByJobId(jobId);
    }
}
