package com.example.jobhunt.service.admin;

import com.example.jobhunt.entity.Applicant;
import com.example.jobhunt.entity.Company;
import com.example.jobhunt.entity.Job;
import com.example.jobhunt.exception.NotFoundException;
import com.example.jobhunt.repo.ApplicantRepo;
import com.example.jobhunt.repo.CompanyRepo;
import com.example.jobhunt.repo.JobRepo;
import com.example.jobhunt.request.CreateApplicantRequest;
import com.example.jobhunt.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicantAdminService {
    private final CompanyRepo companyRepo;
    private final JobRepo jobRepo;
    private final ApplicantRepo applicantRepo;
    private final FileService fileService;

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

    public void createApplicant(int companyId, int jobId, CreateApplicantRequest request) {
        Company company = companyRepo.findById(companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công ty có id = " + companyId);
        });

        Job job = jobRepo.findByIdAndCompanyId(jobId, companyId).orElseThrow(() -> {
            throw new NotFoundException("Không tìm thấy công việc có id = " + jobId);
        });

        // B1 : Upload file
//        String filePath = fileService.uploadFile(companyId, request.getFile());

        // B2 : Cập nhật lại image cho job
        Applicant applicant = new Applicant();
        applicant.setName(request.getName());
        applicant.setDescription(request.getDescription());
//        applicant.setCvPath(filePath);
        applicant.setJobId(jobId);

        applicantRepo.save(applicant);
    }
}
