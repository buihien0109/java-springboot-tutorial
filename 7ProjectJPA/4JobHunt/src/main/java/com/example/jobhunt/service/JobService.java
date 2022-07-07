package com.example.jobhunt.service;

import com.example.jobhunt.dto.CompanyDto;
import com.example.jobhunt.model.Applicant;
import com.example.jobhunt.model.Company;
import com.example.jobhunt.model.Job;
import com.example.jobhunt.dto.JobInfo;
import com.example.jobhunt.repo.ApplicantRepo;
import com.example.jobhunt.repo.CompanyRepo;
import com.example.jobhunt.repo.JobRepo;
import com.example.jobhunt.request.CreateApplicantRequest;
import com.example.jobhunt.request.CreateJobRequest;
import com.example.jobhunt.request.UpdateJobRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private FileService fileService;

    @Autowired
    private ApplicantRepo applicantRepo;

    // Lấy danh sách tất cả các job
    // Kiểu trả về là JobInfo
    public List<JobInfo> getAll() {
        List<Job> jobs =  jobRepo.findAll();

        return jobs.stream().map(job -> {
            JobInfo jobInfo = new JobInfo();
            jobInfo.setId(job.getId());
            jobInfo.setTitle(job.getTitle());
            jobInfo.setDescription(job.getDescription());
            jobInfo.setSkills(job.getSkills());
            jobInfo.setSalary(job.getSalary());

            Company company = companyRepo.getById(job.getCompanyId());
            jobInfo.setCompanyDto(new CompanyDto(company.getId(), company.getName(), company.getLogoPath(), company.getCity(), company.getDescription()));

            return jobInfo;
        }).collect(Collectors.toList());
    }

    // Lấy danh sách job theo điều kiện
    public List<JobInfo> getAll(Optional<String> skill, Optional<String> city) {
        List<JobInfo> jobInfos = getAll();

        if(skill.isPresent()) {
            jobInfos = jobInfos
                    .stream()
                    .filter(jobInfo -> jobInfo.getSkills().contains(skill.get()))
                    .collect(Collectors.toList());
        }

        if(city.isPresent()) {
            jobInfos = jobInfos
                    .stream()
                    .filter(jobInfo -> jobInfo.getCompanyDto().getCity().equals(city.get()))
                    .collect(Collectors.toList());
        }

        return jobInfos;
    }

    // Lấy thông tin job theo id
    public Job getJobById(int id) {
        return jobRepo.getJobById(id);
    }

    // Lấy thông tin job theo id
    // Kiểu trả về là JobInfo
    public JobInfo getJobInfoById(int id) {
        Job job = jobRepo.getJobById(id);

        JobInfo jobInfo = new JobInfo();
        jobInfo.setId(job.getId());
        jobInfo.setTitle(job.getTitle());
        jobInfo.setDescription(job.getDescription());
        jobInfo.setSkills(job.getSkills());
        jobInfo.setSalary(job.getSalary());

        Company company = companyRepo.getById(job.getCompanyId());
        jobInfo.setCompanyDto(new CompanyDto(company.getId(), company.getName(), company.getLogoPath(), company.getCity(), company.getDescription()));

        return jobInfo;
    }

    // Lấy danh sách job theo công ty
    public List<Job> getJobsByCompanyId(int companyId) {
        return jobRepo.getJobsByCompanyId(companyId);
    }

    // Tạo job mới
    public Job createJob(CreateJobRequest request) {
        Random rd = new Random();

        Job job = new Job();
        job.setId(rd.nextInt(100));
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSkills(request.getSkills());
        job.setSalary(request.getSalary());
        job.setCompanyId(request.getCompanyId());

        jobRepo.save(job);

        return job;
    }

    // Cập nhật thông tin job
    public Job updateJob(int id, UpdateJobRequest request) {
        Job job = jobRepo.getJobById(id);

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setSkills(request.getSkills());
        job.setSalary(request.getSalary());

        return job;
    }

    // Xóa job
    public void deleteJob(int id) {
        Job job = jobRepo.getJobById(id);
        jobRepo.delete(job.getId());
    }

    // Ứng tuyển job
    public void applyJob(int id, String applicantString, MultipartFile file) {
        // Lấy thông tin job
        Job job = jobRepo.getJobById(id);

        // Upload cv của người ứng tuyển
        String cvPath = fileService.uploadFile(job.getCompanyId(), file);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Applicant applicant = objectMapper.readValue(applicantString, Applicant.class);

            // Tạo applicant
            Random rd = new Random();
            applicant.setId(rd.nextInt(100));
            applicant.setCvPath(cvPath);
            applicant.setJobId(id);

            applicantRepo.save(applicant);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
