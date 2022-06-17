package com.example.jobhunt.service.admin;

import com.example.jobhunt.entity.Applicant;
import com.example.jobhunt.entity.Company;
import com.example.jobhunt.entity.Job;
import com.example.jobhunt.exception.NotFoundException;
import com.example.jobhunt.repo.ApplicantRepo;
import com.example.jobhunt.repo.CompanyRepo;
import com.example.jobhunt.repo.JobRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantAdminService {
    private final CompanyRepo companyRepo;
    private final JobRepo jobRepo;
    private final ApplicantRepo applicantRepo;

    public List<Applicant> getApplicants(int companyId, int jobId) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        Job job = jobRepo.findByIdAndCompanyId(jobId, companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        return applicantRepo.getApplicantsByJobId(jobId);
    }

    public void deleteApplicant(int companyId, int jobId, int id) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        Job job = jobRepo.findByIdAndCompanyId(jobId, companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        Applicant applicant = applicantRepo.findByIdAndJobId(id, jobId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        applicantRepo.delete(applicant);
    }
}
