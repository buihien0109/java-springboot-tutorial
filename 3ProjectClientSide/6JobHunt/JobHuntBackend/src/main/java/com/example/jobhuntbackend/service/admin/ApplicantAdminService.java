package com.example.jobhuntbackend.service.admin;

import com.example.jobhuntbackend.exception.NotFoundException;
import com.example.jobhuntbackend.model.Applicant;
import com.example.jobhuntbackend.model.Job;
import com.example.jobhuntbackend.repo.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantAdminService {
    private final ApplicantRepo applicantRepo;
    private final JobAdminService jobAdminService;

    public List<Applicant> getApplicants(int companyId, int jobId) {
        Job job = jobAdminService.findJob(companyId, jobId);
        return applicantRepo.getByJobId(jobId);
    }

    public void deleteApplicant(int companyId, int jobId, int id) {
        Applicant applicant = findApplicant(companyId, jobId, id);
        applicantRepo.delete(id);
    }

    // HELPER METHOD
    // Tìm kiếm job theo companyId và jobId
    public Applicant findApplicant(int companyId, int jobId, int applicantId) {
        // Lấy thông tin của job
        Job job = jobAdminService.findJob(companyId, jobId);

        // Lấy thông tin của ứng viên
        Applicant applicant = applicantRepo.getById(applicantId);

        // Kiểm tra job có thuộc company hay không
        if (job.getId() != applicant.getJobId()) {
            throw new NotFoundException("Ứng viên có id = " + applicantId + " không thuộc công việc có id = " + jobId);
        }

        return applicant;
    }
}
