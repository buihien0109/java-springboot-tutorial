package com.example.jobhuntbackend.service.admin;

import com.example.jobhuntbackend.model.Applicant;
import com.example.jobhuntbackend.repo.ApplicantRepo;
import com.example.jobhuntbackend.repo.JobRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantAdminService {
    private final ApplicantRepo applicantRepo;
    private final JobRepo jobRepo;

    public List<Applicant> getAll() {
        return applicantRepo.getAll();
    }

    public Applicant getApplicantById(int id) {
        return applicantRepo.getById(id);
    }

    public List<Applicant> getApplicantsByJobId(int jobId) {
        return null;
    }

    public void deleteApplicant(int id) {

    }
}
